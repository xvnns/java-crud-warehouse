package com.example.crudwarehouse.search.criteria;

import com.example.crudwarehouse.search.predicate.PredicateStrategy;
import com.example.crudwarehouse.search.predicate.StringPredicateStrategy;

public class StringSearchCriteria extends SearchCriteria<String> {
    private static final PredicateStrategy<String> STRATEGY = new StringPredicateStrategy();

    @Override
    public PredicateStrategy<String> getStrategy() {
        return STRATEGY;
    }
}
