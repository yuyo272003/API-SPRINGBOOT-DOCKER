package com.example.copsboot.user;
/*
Al crear esta interfaz, tenemos un repositorio que permite guardar, editar, eliminar y encontrar registros de User en tiempo de ejecución
 */
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;
public interface UserRepository extends CrudRepository<User, UUID>{
    
}
