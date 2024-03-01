package com.microservice.authservice.dto.requestDto;

import lombok.Data;

@Data
public class RefreshTokenRequestDto {

    private String token;
}
