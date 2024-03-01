package com.microservice.authservice.service;

import com.microservice.authservice.dto.requestDto.ChangeRoleRequestDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    boolean isExistUser(long userId);

    String changeRole(ChangeRoleRequestDto changeRoleRequestDto, long userId);
}
