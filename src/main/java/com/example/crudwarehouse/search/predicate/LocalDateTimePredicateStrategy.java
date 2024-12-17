package com.example.crudwarehouse.search.predicate;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDateTime;

public class LocalDateTimePredicateStrategy implements PredicateStrategy<LocalDateTime> {
    @Override
    public Predicate getEqualPredicate(Expression<LocalDateTime> expression, LocalDateTime value, CriteriaBuilder cb) {
        return cb.equal(cb.function("date", LocalDateTime.class, expression), value.toLocalDate());
    }

    @Override
    public Predicate getGreaterThanOrEqualToPredicate(Expression<LocalDateTime> expression, LocalDateTime value, CriteriaBuilder cb) {
        return cb.greaterThanOrEqualTo(expression, value);
    }

    @Override
    public Predicate getLessThanOrEqualToPredicate(Expression<LocalDateTime> expression, LocalDateTime value, CriteriaBuilder cb) {
        return cb.lessThanOrEqualTo(expression, value);
    }

    @Override
    public Predicate getLikePredicate(Expression<LocalDateTime> expression, LocalDateTime value, CriteriaBuilder cb) {
        return cb.between(expression, value.minusDays(3), value.plusDays(3));
    }
}
