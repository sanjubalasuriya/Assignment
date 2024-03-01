package com.microservice.authservice.controller;


import com.microservice.authservice.dto.requestDto.RefreshTokenRequestDto;
import com.microservice.authservice.dto.requestDto.SignInRequestDto;
import com.microservice.authservice.dto.requestDto.SignUpRequestDto;
import com.microservice.authservice.dto.responseDto.JwtAuthenticationResponseDto;
import com.microservice.authservice.model.User;
import com.microservice.authservice.service.AuthenticationService;
import com.microservice.authservice.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<StandardResponse> signup(@RequestBody SignUpRequestDto signUpRequestDto){
        String message = authenticationService.signUp(signUpRequestDto);
        return new ResponseEntity<StandardResponse>(new StandardResponse(201, "success", message)
                , HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto){
        return ResponseEntity.ok(authenticationService.signIn(signInRequestDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequestDto));
    }
}
