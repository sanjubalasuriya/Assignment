package com.microservice.authservice.service;


import com.microservice.authservice.dto.requestDto.RefreshTokenRequestDto;
import com.microservice.authservice.dto.requestDto.SignInRequestDto;
import com.microservice.authservice.dto.requestDto.SignUpRequestDto;
import com.microservice.authservice.dto.responseDto.JwtAuthenticationResponseDto;
import com.microservice.authservice.model.User;

public interface AuthenticationService {



    JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto);

    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);

    String signUp(SignUpRequestDto signUpRequestDto);
}
