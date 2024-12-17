package com.example.crudwarehouse.search.criteria;

import com.example.crudwarehouse.search.predicate.BigDecimalPredicateStrategy;
import com.example.crudwarehouse.search.predicate.PredicateStrategy;

import java.math.BigDecimal;

public class BigDecimalSearchCriteria extends SearchCriteria<BigDecimal> {
    private static final PredicateStrategy<BigDecimal> STRATEGY = new BigDecimalPredicateStrategy();

    @Override
    public PredicateStrategy<BigDecimal> getStrategy() {
        return STRATEGY;
    }
}
