package com.example.authentication.enums;

import java.util.Set;

public enum UserRole {
    ADMIN(Set.of("ROLE_ADMIN", "ROLE_USER")),
    USER(Set.of("ROLE_USER"));

    private final Set<String> roles;

    UserRole(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
