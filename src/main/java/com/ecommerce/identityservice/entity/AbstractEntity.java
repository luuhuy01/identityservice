package com.ecommerce.identityservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass // map extends properties to super class
public abstract class AbstractEntity {
    @CreationTimestamp
    @Column(name = "created_at")
    protected ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    protected ZonedDateTime updatedAt;

    @Column(name = "updated_by")
    protected String updatedBy;

    @Column(name = "created_by")
    protected String createdBy;
}
