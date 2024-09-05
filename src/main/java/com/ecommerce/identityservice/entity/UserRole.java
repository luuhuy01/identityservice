package com.ecommerce.identityservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "user_role")
@Getter
@Setter
public class UserRole extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_role_id")
    private String userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
