package com.example.crudwarehouse.search;

import com.example.crudwarehouse.model.Product;
import com.example.crudwarehouse.search.criteria.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class ProductSpecification implements Specification<Product> {
    private final List<SearchCriteria> criteriaList;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = criteriaList.stream().map(
                criteria -> {
                    switch (criteria.getOperation()) {
                        case EQUAL -> {
                            return criteria.getStrategy().getEqualPredicate(
                                    root.get(criteria.getField()), criteria.getValue(), criteriaBuilder
                            );
                        }
                        case GRATER_THAN_OR_EQUAL_TO -> {
                            return criteria.getStrategy().getGreaterThanOrEqualToPredicate(
                                    root.get(criteria.getField()), criteria.getValue(), criteriaBuilder
                            );
                        }
                        case LESS_THAN_OR_EQUAL_TO -> {
                            return criteria.getStrategy().getLessThanOrEqualToPredicate(
                                    root.get(criteria.getField()), criteria.getValue(), criteriaBuilder
                            );
                        }
                        case LIKE -> {
                            return criteria.getStrategy().getLikePredicate(
                                    root.get(criteria.getField()), criteria.getValue(), criteriaBuilder
                            );
                        }
                    }

                    return null;
                }
        ).toList();

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}