package com.ecommerce.identityservice.utils;

import com.ecommerce.identityservice.dto.response.common.ApiResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResponseUtil {

    public static ApiResponse ok(Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);
        apiResponse.setCode(200);
        return apiResponse;
    }

    public static ApiResponse response(Object data, String message, int code) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        return apiResponse;
    }

//    public static <T> ApiPageResponse<T> ok(Page<T> tList, int page, int size) {
//        P
//        ApiPageResponse<T> apiPageResponse = new ApiPageResponse<>();
//        apiPageResponse.setData(tList);
//        apiPageResponse.setPageNum(page);
//        apiPageResponse.setPageSize(size);
//        apiPageResponse.setCode(200);
//        return apiPageResponse;
//    }
}
