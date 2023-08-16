package com.example.authentication.auth;

import com.example.authentication.user.User;
import com.example.authentication.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public void login(AuthRequestDTO authRequest) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.login(), authRequest.password())
        );
    }

    public User register(AuthRegisterRequestDTO authRegisterRequest) {
        if (userService.existsByLogin(authRegisterRequest.login())) return null;

        String encodedPassword = new BCryptPasswordEncoder().encode(authRegisterRequest.password());

        return userService.save(new User(authRegisterRequest.login(), encodedPassword, authRegisterRequest.role()));
    }
}
