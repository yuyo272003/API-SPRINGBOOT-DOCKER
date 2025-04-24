package com.example.copsboot.user;

/* Persistencia es guardar datos en una base de datos
Un repositorio es una clase o interfaz encargada de acceder a la base de datos, leer, guardar, actualizar, eliminar, etc.
La persistencia real ocurre a través de un repositorio.
El trabajo pesado del patrón de repositorio ya está implementado en Spring Data.
Usarlo es tan simple como definir una interfaz que extienda de CrudRepository.
Al crear esta interfaz, tenemos un repositorio que permite guardar, editar, eliminar y encontrar registros de User en tiempo de ejecución
 */
import org.springframework.data.repository.CrudRepository;

// import java.util.UUID;


public interface UserRepository extends CrudRepository<User, UserId>, UserRepositoryCustom {
    
    
}
