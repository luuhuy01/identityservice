package com.ecommerce.identityservice.dto.response.common;

import lombok.Data;

@Data
public class ApiResponse {
    private int code;
    private String message;
    private String messageCode;
    private Object data;
}
