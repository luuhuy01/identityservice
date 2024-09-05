package com.ecommerce.identityservice.config.builder;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PredicateConcreteBuilder implements PredicateBuilder {

    protected List<Predicate> predicates = new ArrayList<>();
    protected CriteriaBuilder criteriaBuilder;

    public PredicateConcreteBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public static PredicateBuilder criteriaBuilder(CriteriaBuilder cb) {
        return new PredicateConcreteBuilder(cb);
    }

    @Override
    public PredicateBuilder iLike(Path<String> field, String value) {
        if (StringUtils.isNotEmpty(value)) {
            this.predicates.add(this.criteriaBuilder.like(this.criteriaBuilder.lower(field), "%" + value.toLowerCase() + "%"));
        }
        return this;
    }

    @Override
    public PredicateBuilder iLike(List<Path<String>> fields, String value) {
        if (StringUtils.isNotEmpty(value)) {
            List<Predicate> predicates = new ArrayList<>();
            for (Path<String> field : fields) {
                predicates.add(this.criteriaBuilder.like(this.criteriaBuilder.lower(field), "%" + value.toLowerCase() + "%"));
            };
            Predicate orPredicate = criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            this.predicates.add(orPredicate);
        }
        return this;
    }

    @Override
    public PredicateBuilder equal(Path<String> field, Object value) {
        if (value != null) {
            this.predicates.add(this.criteriaBuilder.equal(field, value));
        }
        return this;
    }

    @Override
    public PredicateBuilder notEqual(Path<String> field, Object value) {
        if (value != null) {
            this.predicates.add(this.criteriaBuilder.notEqual(field, value));
        }
        return this;
    }

    @Override
    public <T extends Comparable<? super T>> PredicateBuilder greater(Path<? extends T> field, T value) {
        if (value != null) {
            this.predicates.add(this.criteriaBuilder.greaterThan(field, value));
        }
        return null;
    }

    @Override
    public <T extends Comparable<? super T>> PredicateBuilder less(Path<? extends T> field, T value) {
        return null;
    }

    @Override
    public <T extends Comparable<? super T>> PredicateBuilder greaterOrEqual(Path<? extends T> field, T value) {
        return null;
    }

    @Override
    public <T extends Comparable<? super T>> PredicateBuilder lessOrEqual(Path<? extends T> field, T value) {
        return null;
    }

    @Override
    public <T extends Comparable<? super T>> PredicateBuilder between(Path<? extends T> field, T min, T max) {
        return null;
    }

    @Override
    public PredicateBuilder between(Path<Instant> field, Instant min, Instant max) {
        return null;
    }

    @Override
    public PredicateBuilder beforeOrEqual(Path<Instant> field, Instant value) {
        return null;
    }

    @Override
    public PredicateBuilder afterOrEqual(Path<Instant> field, Instant value) {
        return null;
    }

    @Override
    public PredicateBuilder in(Path<String> field, String... values) {
        return null;
    }

    @Override
    public Predicate[] build() {
        return this.predicates.toArray(new Predicate[0]);
    }
}
