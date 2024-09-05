package com.ecommerce.identityservice.dto.request;

import com.ecommerce.identityservice.config.builder.PredicateConcreteBuilder;
import com.ecommerce.identityservice.entity.User;
import com.ecommerce.identityservice.entity.User_;
import jakarta.persistence.criteria.Predicate;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserCriteria {

    private String keyword;
    private String name;
    private String email;
    private String username;
    private LocalDate birthday;

    public Specification<User> toUserSpecification() {
        return (root, query, cb) -> {
            Predicate[] predicates = PredicateConcreteBuilder
                    .criteriaBuilder(cb)
                    .iLike(List.of(root.get(User_.NAME), root.get(User_.USERNAME)), this.keyword)
                    .iLike(root.get(User_.NAME), this.name)
                    .iLike(root.get(User_.EMAIL), this.email)
                    .iLike(root.get(User_.USERNAME), this.username)
                    .equal(root.get(User_.BIRTHDAY), this.birthday)
                    .build();

            return cb.and(predicates);
        };
    }
}
