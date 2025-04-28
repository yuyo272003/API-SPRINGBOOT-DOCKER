// src/main/java/com/example/copsboot/user/web/CreateUserRequest.java
package com.example.copsboot.user.web;

import com.example.copsboot.user.CreateUserParameters;
import com.example.copsboot.user.AuthServerId;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public record CreateUserRequest(String mobileToken) {
    public CreateUserParameters toParameters(Jwt jwt) {
        AuthServerId authServerId = new AuthServerId(UUID.fromString(jwt.getSubject()));
        String email        = jwt.getClaimAsString("email");
        return new CreateUserParameters(authServerId, email, mobileToken);
    }
}
