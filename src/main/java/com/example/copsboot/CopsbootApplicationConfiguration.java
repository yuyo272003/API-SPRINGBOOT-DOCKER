package com.example.copsboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.example.orm.jpa.UniqueIdGenerator;
import java.util.UUID;
import com.example.orm.jpa.InMemoryUniqueIdGenerator;

@Configuration
public class CopsbootApplicationConfiguration {
    @Bean
    public UniqueIdGenerator<UUID> uniqueIdGenerator() {
    return new InMemoryUniqueIdGenerator();
    }
}