package com.example.crudwarehouse.search.criteria;

import com.example.crudwarehouse.search.predicate.LocalDateTimePredicateStrategy;
import com.example.crudwarehouse.search.predicate.PredicateStrategy;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
public class LocalDateTimeSearchCriteria extends SearchCriteria<LocalDateTime> {
    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final PredicateStrategy<LocalDateTime> STRATEGY = new LocalDateTimePredicateStrategy();

    private String value; //fixme use @JsonFormat

    @Override
    public LocalDateTime getValue() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
        return LocalDateTime.parse(value, formatter);
    }

    @Override
    public PredicateStrategy<LocalDateTime> getStrategy() {
        return STRATEGY;
    }
}


