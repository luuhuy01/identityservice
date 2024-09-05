package com.ecommerce.identityservice.controller;

import com.ecommerce.identityservice.dto.request.UserCreateRequest;
import com.ecommerce.identityservice.dto.request.UserCriteria;
import com.ecommerce.identityservice.dto.request.common.PagingReq;
import com.ecommerce.identityservice.dto.response.UserResponse;
import com.ecommerce.identityservice.dto.response.common.ApiResponse;
import com.ecommerce.identityservice.dto.response.common.PagingResponse;
import com.ecommerce.identityservice.service.UserService;
import com.ecommerce.identityservice.utils.ResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ApiResponse getUser(@PathVariable String id) {
        UserResponse user = userService.getUserById(id);
        return ResponseUtil.ok(user);
    }

    @PostMapping("/create")
    public ApiResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserResponse user = userService.saveUser(userCreateRequest);
        return ResponseUtil.ok(user);
    }

    @GetMapping("/search")
    public ApiResponse searchUser(@Valid UserCriteria userCriteria, PagingReq pagingReq) {
        log.info("Searching user with criteria {}, pagingReq: {}", userCriteria, pagingReq);
        return ResponseUtil.ok(PagingResponse.of(userService.searchUser(userCriteria, pagingReq)));
    }
}
