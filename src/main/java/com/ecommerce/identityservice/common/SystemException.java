package com.ecommerce.identityservice.common;

import lombok.Data;

@Data
public class SystemException extends RuntimeException {
    protected int code;

    public SystemException(int code) {
        this.code = code;
    }

    public SystemException(int code, String message) {
        super(message);
        this.code = code;
    }

}
