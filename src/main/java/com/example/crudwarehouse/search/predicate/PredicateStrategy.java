package com.example.crudwarehouse.search.predicate;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

public interface PredicateStrategy<T> {
    Predicate getEqualPredicate(Expression<T> expression, T value, CriteriaBuilder cb);

    Predicate getGreaterThanOrEqualToPredicate(Expression<T> expression, T value, CriteriaBuilder cb);

    Predicate getLessThanOrEqualToPredicate(Expression<T> expression, T value, CriteriaBuilder cb);

    Predicate getLikePredicate(Expression<T> expression, T value, CriteriaBuilder cb);
}
