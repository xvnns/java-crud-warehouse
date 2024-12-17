package com.example.crudwarehouse.search.predicate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

public class StringPredicateStrategy implements PredicateStrategy<String> {
    @Override
    public Predicate getEqualPredicate(Expression<String> expression, String value, CriteriaBuilder cb) {
        return cb.equal(expression, value);
    }

    @Override
    public Predicate getGreaterThanOrEqualToPredicate(Expression<String> expression, String value, CriteriaBuilder cb) {
        return cb.greaterThanOrEqualTo(expression, value + "%");
    }

    @Override
    public Predicate getLessThanOrEqualToPredicate(Expression<String> expression, String value, CriteriaBuilder cb) {
        return cb.lessThanOrEqualTo(expression, "%" + value);
    }

    @Override
    public Predicate getLikePredicate(Expression<String> expression, String value, CriteriaBuilder cb) {
        return cb.like(expression, "%" + value + "%");
    }
}
