package com.microservice.authservice.service.serviceImpl;


import com.microservice.authservice.dto.requestDto.RefreshTokenRequestDto;
import com.microservice.authservice.dto.requestDto.SignInRequestDto;
import com.microservice.authservice.dto.requestDto.SignUpRequestDto;
import com.microservice.authservice.dto.responseDto.JwtAuthenticationResponseDto;
import com.microservice.authservice.model.Role;
import com.microservice.authservice.model.User;
import com.microservice.authservice.repository.UserRepository;
import com.microservice.authservice.security.JWTService;
import com.microservice.authservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public String signUp(SignUpRequestDto signUpRequestDto) {
        User user = new User();

        user.setEmail(signUpRequestDto.getEmail());
        user.setName(signUpRequestDto.getName());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setRole(Role.USER);

         userRepository.save(user);
        return "Saved user";
    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(), signInRequestDto.getPassword()));

        var user = userRepository.findByEmail(signInRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Email or Password"));



        var jwt = jwtService.createToken(user, user.getRole());
        var refreshToken = jwtService.createRefreshToken(user, user.getRole());

        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
        jwtAuthenticationResponseDto.setToken(jwt);
        jwtAuthenticationResponseDto.setRefreshToken(refreshToken);
        return jwtAuthenticationResponseDto;
    }

    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto){
        String userEmail = jwtService.extractUsername(refreshTokenRequestDto.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequestDto.getToken(),user)){
            var jwt = jwtService.createToken(user, user.getRole());

            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();

            jwtAuthenticationResponseDto.setToken(jwt);
            jwtAuthenticationResponseDto.setRefreshToken(refreshTokenRequestDto.getToken());
            return jwtAuthenticationResponseDto;
        }

        return null;
    }
}
