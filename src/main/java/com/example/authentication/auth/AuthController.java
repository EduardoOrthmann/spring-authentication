package com.example.authentication.auth;

import com.example.authentication.user.User;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDTO authRequest) {
        return authService.login(authRequest)
                .map(auth -> ResponseEntity.ok().header("Authorization", auth).build())
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<?> register(@RequestBody @Valid AuthRegisterRequestDTO authRegisterRequest) {
        User user = authService.register(authRegisterRequest);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }
}
