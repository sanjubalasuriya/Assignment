package com.microservice.authservice.dto.responseDto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {

    private String token;

    private String refreshToken;
}
