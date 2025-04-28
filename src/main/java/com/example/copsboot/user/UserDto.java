// src/main/java/com/example/copsboot/user/UserDto.java
package com.example.copsboot.user;

public record UserDto(
        String userId,
        String email,
        String authServerId,
        String mobileToken
) {
    public static UserDto fromUser(User u) {
        return new UserDto(
                u.getId().asString(),
                u.getEmail(),
                u.getAuthServerId().value().toString(),
                u.getMobileToken()
        );
    }
}
