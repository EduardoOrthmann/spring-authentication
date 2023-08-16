package com.example.authentication.auth;

import com.example.authentication.enums.UserRole;

public record AuthRegisterRequestDTO(String login, String password, UserRole role) {
}
