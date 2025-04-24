package com.example.copsboot.user;

import java.util.UUID;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.orm.jpa.InMemoryUniqueIdGenerator;
import com.example.orm.jpa.UniqueIdGenerator;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest 
/* 
 Le indica al soporte de pruebas empezar unicamente la parte de la aplicacipon que está relacionado con JPA
 */
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;
    /*
     * Crea una instancia de UserRepository para que se pueda usar en las pruebas
     */

    @Test
    /* 
     * Método que contiene las pruebas
     */
    public void testStoreUser(){
        HashSet<UserRole> roles = new HashSet<>();
        roles.add(UserRole.OFFICER);
        // User user = repository.save(new User(UUID.randomUUID(), "ingrid@officer.com", "pwd", roles)); utilizando UUID
        User user = repository.save(new User(repository.nextId(),
                                                "ingrid@officer.com",
                                                "pwd",
                                                roles));
        /*
         * Aquí guarda la entidad User en la base de datos
         */

        assertThat(user).isNotNull();
        /*
         * El objeto que se retorna del método sabe del repositorio debería ser un no null
         */

        assertThat(repository.count()).isEqualTo(1L);
        /*
         * Si se cuentan los números de entidades User en la base de datos, 
         * solo debería haber una
         */
    }
    @TestConfiguration
    static class TestConfig {
        @Bean
        public UniqueIdGenerator<UUID> generator() {
            return new InMemoryUniqueIdGenerator();
        }
    }
}
