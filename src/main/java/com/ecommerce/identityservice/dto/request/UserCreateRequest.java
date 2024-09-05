package com.ecommerce.identityservice.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateRequest {
    private String name;
    private String email;
    private String username;
    private String password;
    private LocalDate birthday;
    private String roles;
}
