// src/main/java/com/example/copsboot/user/AuthServerId.java
package com.example.copsboot.user;

import java.util.UUID;
import org.springframework.util.Assert;

public record AuthServerId(UUID value) {
    public AuthServerId {
        Assert.notNull(value, "El valor de AuthServerId no debe ser null");
    }
}
