package com.store.ezbuy.controller;

import com.store.ezbuy.auth.entity.RefreshToken;
import com.store.ezbuy.auth.entity.User;
import com.store.ezbuy.auth.service.AuthService;
import com.store.ezbuy.auth.service.JwtService;
import com.store.ezbuy.auth.service.RefreshTokenService;
import com.store.ezbuy.auth.utils.AuthResponse;
import com.store.ezbuy.auth.utils.LoginRequest;
import com.store.ezbuy.auth.utils.RefreshTokenRequest;
import com.store.ezbuy.auth.utils.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtService jwtService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest registrationRequest){
        return new ResponseEntity<>(authService.register(registrationRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());

        User user = refreshToken.getUser();

        String accessToken = jwtService.generateToken(user);

        return new ResponseEntity<>(AuthResponse
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build(),HttpStatus.OK);
    }
}
