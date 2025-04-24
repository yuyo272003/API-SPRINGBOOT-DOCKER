// Clase que contiene todas las propiedades de los usuarios
package com.example.copsboot.user;
import java.util.Set;
// import java.util.UUID;

import com.example.orm.jpa.AbstractEntity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Enumerated;
// import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity /*
(que representa una tabla en una base de datos)
marca la clase como una entidad que puede guardar y 
gestionar en la base de datos automáticamente (persistencia)
Convierte la clase en una entidad, que puede guardar, leer, 
actualizar y borrarla de la base de datos
Los atributos de la clase son las columnas

*/

@Table(name = "copsboot_user")
/*
Es opcional, permite asignar explicitamente el nombre de la tabla. 
Si no se especifica el nombre, Spring boot le asignará el nombre de la clase con snake_case
*/ 
public class User extends AbstractEntity<UserId> {
    /*
    @Id
    private UUID id;
    Define la llave primaria de la entidad */
    private String email;
    
    private String password;

     @ElementCollection(fetch = FetchType.EAGER)
     @Enumerated(EnumType.STRING) // Se asegura de que los calores almacenados sean de tipo String
     @NotNull
    private Set<UserRole> roles;
    /* El campo roles es una colección de valores enum */

    protected User(){}
    /* Hibernate necesita un constructor sin argumentos, así que agregamos este, no necesita ser público */
    
    public User(UserId id, String email, String password, Set<UserRole> roles) {
        super(id);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    // public UUID getId() {
    //     return id;
    // }
    // public void setId(UUID id) {
    //     this.id = id;
    // }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<UserRole> getRoles() {
        return roles;
    }
    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    

    
}
