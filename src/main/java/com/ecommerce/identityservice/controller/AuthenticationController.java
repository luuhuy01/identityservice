package com.ecommerce.identityservice.controller;

import com.ecommerce.identityservice.dto.request.AuthenticationRequest;
import com.ecommerce.identityservice.dto.request.IntrospectRequest;
import com.ecommerce.identityservice.dto.response.AuthenticationResponse;
import com.ecommerce.identityservice.dto.response.common.ApiResponse;
import com.ecommerce.identityservice.service.AuthenticationService;
import com.ecommerce.identityservice.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(authenticationRequest);
        return ResponseUtil.ok(authenticationResponse);
    }

    @PostMapping("/introspect")
    public ApiResponse introspect(@RequestBody IntrospectRequest introspectRequest) {
        return ResponseUtil.ok(authenticationService.introspect(introspectRequest));
    }

    @PostMapping("/logout")
    public ApiResponse logout(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseUtil.ok(authenticationService.authenticate(authenticationRequest));
    }
}
