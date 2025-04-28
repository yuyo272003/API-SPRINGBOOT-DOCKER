package com.example.copsboot.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Crea un nuevo usuario en la aplicación.
     *
     * @param params los parámetros necesarios para crear el usuario,
     *               incluyen el AuthServerId (UUID del Identity Provider),
     *               el email y el mobileToken.
     * @return la entidad User recién guardada.
     * @throws IllegalStateException si ya existe un usuario con ese AuthServerId.
     */
    @Transactional
    public User createUser(CreateUserParameters params) {
        // 1) Verificar que no exista ya un usuario con ese AuthServerId
        Optional<User> existing = userRepository.findByAuthServerId(params.authServerId());
        if (existing.isPresent()) {
            throw new IllegalStateException(
                    "Ya existe un usuario con AuthServerId = " + params.authServerId().value()
            );
        }

        // 2) Generar un nuevo UserId
        UserId newId = userRepository.nextId();

        // 3) Construir la entidad y guardarla
        User user = new User(
                newId,
                params.email(),
                params.authServerId(),
                params.mobileToken()
        );
        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su AuthServerId (el subject del JWT).
     *
     * @param authServerId el ID que viene en el JWT.
     * @return Optional<User> con el usuario si existe.
     */
    @Transactional(readOnly = true)
    public Optional<User> findByAuthServerId(AuthServerId authServerId) {
        return userRepository.findByAuthServerId(authServerId);
    }
}

