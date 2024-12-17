package com.example.crudwarehouse.scheduler;

import com.example.crudwarehouse.annotation.MeasureExecutionTime;
import com.example.crudwarehouse.model.Product;
import com.example.crudwarehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Component
@Profile("!local")
@ConditionalOnExpression(value = "#{'${app.scheduling.mode:none}'.equals('simple')}")
public class SimpleIncreasePriceScheduler {

    private final ProductRepository productRepository;
    private final Double priceIncreasePercentage;

    public SimpleIncreasePriceScheduler(ProductRepository productRepository,
                                        @Value("${app.scheduling.priceIncreasePercentage:10}") Double priceIncreasePercentage) {
        this.productRepository = productRepository;
        this.priceIncreasePercentage = priceIncreasePercentage;
    }

    @Transactional
    @MeasureExecutionTime
    @Scheduled(fixedRateString = "${app.scheduling.period}")
    public void increasePrice() {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            BigDecimal currentPrice = product.getPrice();
            currentPrice = currentPrice.add(
                    BigDecimal.valueOf(currentPrice.doubleValue() * priceIncreasePercentage / 100)
            );
            product.setPrice(currentPrice);
        });
        productRepository.saveAll(products);
    }
}