package com.ecommerce.identityservice.service;

import com.ecommerce.identityservice.common.BusinessException;
import com.ecommerce.identityservice.dto.constant.ErrorCodeDict;
import com.ecommerce.identityservice.dto.request.AuthenticationRequest;
import com.ecommerce.identityservice.dto.request.IntrospectRequest;
import com.ecommerce.identityservice.dto.response.AuthenticationResponse;
import com.ecommerce.identityservice.dto.response.IntrospectResponse;
import com.ecommerce.identityservice.dto.response.UserResponse;
import com.ecommerce.identityservice.repository.UserRepository;
import com.ecommerce.identityservice.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Value(value = "${security.jwt.sign-key}")
    private String signKey;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UserResponse user = userRepository.findByUsername(request.getUsername())
                .map(user1 -> modelMapper.map(user1, UserResponse.class))
                .orElseThrow(() -> new BusinessException(ErrorCodeDict.USER_NOT_EXISTS));

        long expiresIn = 60 * 60 * 1000;
        var token = JwtUtil.generateToken(user, signKey, expiresIn);
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new BusinessException(ErrorCodeDict.AUTHENTICATE_FAIL);
        }
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setToken(token);
        authenticationResponse.setAuthenticated(matches);
        authenticationResponse.setExpiresIn(expiresIn);
        return authenticationResponse;
    }

    public IntrospectResponse introspect(IntrospectRequest introspectRequest) {
        String token = introspectRequest.getToken();
        IntrospectResponse introspectResponse = new IntrospectResponse();
        boolean verified = JwtUtil.verifyToken(token, signKey);
        introspectResponse.setValid(verified);
        return introspectResponse;
    }
}
