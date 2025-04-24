package com.example.copsboot.user;

public interface UserRepositoryCustom {
    UserId nextId();
    /*
     * Retornará un nuevo objeto de tipo UserId cada vez que es llamado
     */
}
