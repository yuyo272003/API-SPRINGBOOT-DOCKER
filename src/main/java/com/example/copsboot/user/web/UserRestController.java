package com.example.copsboot.user.web;

import com.example.copsboot.user.CreateUserParameters;
import com.example.copsboot.user.User;
import com.example.copsboot.user.UserDto;
import com.example.copsboot.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Map;

@RestController
@RequestMapping("/api/users")                     // ← prefijo común para ambos métodos
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping                                  // ← maneja POST /api/users
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('OFFICER')")
    public UserDto createUser(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateUserRequest request
    ) {
        CreateUserParameters params = request.toParameters(jwt);
        User user = userService.createUser(params);
        return UserDto.fromUser(user);
    }

    @GetMapping("/me")                            // ← maneja GET /api/users/me
    public Map<String,Object> myself(
            @AuthenticationPrincipal Jwt jwt
    ) {
        return Map.of("subject", jwt.getSubject(),
                "claims", jwt.getClaims());
    }
}
