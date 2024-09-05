package com.ecommerce.identityservice.common;

import com.ecommerce.identityservice.dto.constant.ErrorCodeDict;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends SystemException {

    private HttpStatus status;

    public BusinessException(ErrorCodeDict errorCodeDict) {
        super(errorCodeDict.getCode(), errorCodeDict.getMessage());
        this.status = errorCodeDict.getStatus();
    }
}
