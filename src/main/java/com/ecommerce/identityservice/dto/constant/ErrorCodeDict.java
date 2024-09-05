package com.ecommerce.identityservice.dto.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodeDict {
    ERROR_SYSTEM(1001, "Error in System.", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_DUPLICATED(1002, "Username is duplicated.", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(1003, "User not exists.", HttpStatus.BAD_REQUEST),
    AUTHENTICATE_FAIL(1004, "Authenticate fail.", HttpStatus.UNAUTHORIZED),
    ID_NOT_NULL(1005, "Id is required.", HttpStatus.BAD_REQUEST),;


    private final int code;
    private final String message;
    private final HttpStatus status;

    ErrorCodeDict(int code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
