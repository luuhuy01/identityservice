package com.ecommerce.identityservice.config.builder;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

import java.time.Instant;
import java.util.List;

public interface PredicateBuilder {

    PredicateBuilder iLike(Path<String> field, String value);

    PredicateBuilder iLike(List<Path<String>> field, String value);

    PredicateBuilder equal(Path<String> field, Object value);

    PredicateBuilder notEqual(Path<String> field, Object value);

    <T extends Comparable<? super T>> PredicateBuilder greater(Path<? extends T> field, T value);

    <T extends Comparable<? super T>> PredicateBuilder less(Path<? extends T> field, T value);

    <T extends Comparable<? super T>> PredicateBuilder greaterOrEqual(Path<? extends T> field, T value);

    <T extends Comparable<? super T>> PredicateBuilder lessOrEqual(Path<? extends T> field, T value);

    /*
        Equal at both ends
     */
    <T extends Comparable<? super T>> PredicateBuilder between(Path<? extends T> field, T min, T max);

    /*
        Equal at both ends
     */
    PredicateBuilder between(Path<Instant> field, Instant min, Instant max);

    PredicateBuilder beforeOrEqual(Path<Instant> field, Instant value);

    PredicateBuilder afterOrEqual(Path<Instant> field, Instant value);

    PredicateBuilder in(Path<String> field, String... values);

    Predicate[] build();
}
