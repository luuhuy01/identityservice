package com.ecommerce.identityservice.service;

import com.ecommerce.identityservice.common.BusinessException;
import com.ecommerce.identityservice.config.component.MapperTool;
import com.ecommerce.identityservice.dto.constant.ErrorCodeDict;
import com.ecommerce.identityservice.dto.request.UserCreateRequest;
import com.ecommerce.identityservice.dto.request.UserCriteria;
import com.ecommerce.identityservice.dto.request.common.PagingReq;
import com.ecommerce.identityservice.dto.response.UserResponse;
import com.ecommerce.identityservice.entity.User;
import com.ecommerce.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MapperTool mapperTool;
    private final PasswordEncoder passwordEncoder;

    public UserResponse getUserById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException(ErrorCodeDict.ID_NOT_NULL);
        }
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.debug("authentication test: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.debug(grantedAuthority.getAuthority()));

        return userRepository.findById(id)
                .map(user -> mapperTool.map(user, UserResponse.class))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponse saveUser(UserCreateRequest userCreateRequest) {
        Optional<User> userDuplicated = userRepository.findByUsername(userCreateRequest.getUsername());
        if (userDuplicated.isPresent()) {
            throw new BusinessException(ErrorCodeDict.USER_DUPLICATED);
        }

        User user = mapperTool.map(userCreateRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User created = userRepository.save(user);
        return mapperTool.map(created, UserResponse.class);
    }

    public Page<UserResponse> searchUser(UserCriteria userCriteria, PagingReq pagingReq) {
        Page<User> userPage = userRepository.findAll(userCriteria.toUserSpecification(), pagingReq.buildPageable());

//        return userPage.map(user -> {
//            UserResponse userResponse = mapperTool.map(user, UserResponse.class);
//            userResponse.setUserRoles(mapperTool.mapList(user.getUserRoles(), UserRoleResponse.class));
//            return userResponse;
//        });
        return mapperTool.mapPage(userPage, UserResponse.class);
    }

}
