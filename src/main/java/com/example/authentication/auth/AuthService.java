package com.example.authentication.auth;

import com.example.authentication.user.User;
import com.example.authentication.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public Optional<String> login(AuthRequestDTO authRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.login(), authRequest.password())
        );

        if (!authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.of(tokenService.generateToken((User) authentication.getPrincipal()));
    }

    public User register(AuthRegisterRequestDTO authRegisterRequest) {
        if (userService.existsByLogin(authRegisterRequest.login())) return null;

        String encodedPassword = new BCryptPasswordEncoder().encode(authRegisterRequest.password());

        return userService.save(new User(authRegisterRequest.login(), encodedPassword, authRegisterRequest.role()));
    }
}
