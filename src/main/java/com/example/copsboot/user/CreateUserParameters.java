// src/main/java/com/example/copsboot/user/CreateUserParameters.java
package com.example.copsboot.user;

public record CreateUserParameters(
        AuthServerId authServerId,
        String       email,
        String       mobileToken
) { }

