package com.ecommerce.identityservice.dto.request;

import lombok.Data;

@Data
public class IntrospectRequest {
    private String token;
}
