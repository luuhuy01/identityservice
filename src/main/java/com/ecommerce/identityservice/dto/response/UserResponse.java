package com.ecommerce.identityservice.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse implements Serializable {
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;
    private LocalDate birthday;
    private String roles;
    private List<UserRoleResponse> userRoles;
}
