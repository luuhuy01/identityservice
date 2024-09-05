package com.ecommerce.identityservice.controller.advice;

import com.ecommerce.identityservice.common.BusinessException;
import com.ecommerce.identityservice.dto.constant.ErrorCodeDict;
import com.ecommerce.identityservice.dto.response.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionController {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ApiResponse> handleBusinessException(RuntimeException e) {
        log.error(e.getMessage(), e);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(ErrorCodeDict.ERROR_SYSTEM.getMessage());
        apiResponse.setCode(ErrorCodeDict.ERROR_SYSTEM.getCode());

        return ResponseEntity.status(500).body(apiResponse);
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ApiResponse> handleBusinessException(BusinessException e) {
        log.error(e.getMessage(), e);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(e.getMessage());
        apiResponse.setCode(e.getCode());

        return ResponseEntity.status(e.getStatus()).body(apiResponse);
    }

}
