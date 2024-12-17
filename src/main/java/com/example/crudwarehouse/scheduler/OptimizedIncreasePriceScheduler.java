package com.example.crudwarehouse.scheduler;

import com.example.crudwarehouse.annotation.MeasureExecutionTime;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Component
@ConditionalOnExpression(value = "#{'${app.scheduling.mode:none}'.equals('optimized')}")
@Profile("!local")
public class OptimizedIncreasePriceScheduler {

    private final BigDecimal priceIncreasePercentage;
    private final Integer batchSize;
    private final Boolean exclusiveLock;
    private final EntityManagerFactory entityManagerFactory;

    public OptimizedIncreasePriceScheduler(@Value("${app.scheduling.priceIncreasePercentage:10}") BigDecimal priceIncreasePercentage,
                                           @Value("${app.scheduling.batch_size:100000}") Integer batchSize,
                                           @Value("${app.scheduling.optimization.exclusive-lock:false}") Boolean exclusiveLock,
                                           EntityManagerFactory entityManagerFactory) {
        this.priceIncreasePercentage = priceIncreasePercentage;
        this.batchSize = batchSize;
        this.exclusiveLock = exclusiveLock;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Scheduled(fixedRateString = "${app.scheduling.period}")
    @MeasureExecutionTime
    public void increasePrice() {
        Session session = entityManagerFactory.createEntityManager().unwrap(Session.class);
        try (session) {
            session.doWork(connection -> {
                try (connection; PrintWriter writer = new PrintWriter("result.csv", StandardCharsets.UTF_8)) {
                    connection.setAutoCommit(false);

                    if (exclusiveLock) {
                        String lock = "lock table product in access exclusive mode";
                        Statement statement = connection.createStatement();
                        statement.execute(lock);
                    }

                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM product FOR UPDATE");
                    ResultSetMetaData rsMetaData = resultSet.getMetaData();

                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i < rsMetaData.getColumnCount() + 1; i++) {
                        columnNames.add(rsMetaData.getColumnName(i));
                    }
                    writer.println(String.join(",", columnNames));

                    PreparedStatement preparedStatement =
                            connection.prepareStatement("update product set price=? where id=?");


                    int idx = 1;
                    int priceColumnIdx = columnNames.indexOf("price");
                    while (resultSet.next()) {
                        UUID uuid = UUID.fromString(resultSet.getString("id"));
                        BigDecimal oldPrice = new BigDecimal(resultSet.getString("price"));
                        BigDecimal newPrice = calculateNewPrice(oldPrice, priceIncreasePercentage);
                        preparedStatement.setObject(1, newPrice);
                        preparedStatement.setObject(2, uuid);
                        preparedStatement.addBatch();
                        if (idx % batchSize == 0) {
                            executeBatch(preparedStatement, connection, idx);
                        }

                        List<String> line = new ArrayList<>();
                        for (int i = 1; i < priceColumnIdx + 1; i++) {
                            line.add(resultSet.getString(i));
                        }
                        line.add(String.valueOf(newPrice));
                        for (int i = priceColumnIdx + 2; i < rsMetaData.getColumnCount() + 1; i++) {
                            line.add(resultSet.getString(i));
                        }
                        writer.println(String.join(",", line));
                        line.clear();

                        idx++;
                    }
                    if ((idx - 1) % batchSize != 0) {
                        executeBatch(preparedStatement, connection, idx - 1);
                    }
                    connection.commit();
                } catch (Exception e) {
                    connection.rollback();
                    log.error(e.getLocalizedMessage());
                }
            });
        }
    }

    private void executeBatch(PreparedStatement preparedStatement, Connection connection, int idx) throws SQLException {
        try {
            preparedStatement.executeBatch();
            connection.commit();
            log.info("Records processed: " + idx);
            preparedStatement.clearBatch();
            preparedStatement.clearParameters();
        } catch (BatchUpdateException e) {
            connection.rollback();
            log.error(e.getLocalizedMessage());
        }
    }

    private BigDecimal calculateNewPrice(BigDecimal oldPrice, BigDecimal increase) {
        return oldPrice.multiply(BigDecimal.ONE.add(
                increase.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)
        ));
    }
}