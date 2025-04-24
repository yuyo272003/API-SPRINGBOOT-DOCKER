package com.example.orm.jpa;

import java.io.Serializable;
import com.example.util.ArtifactForFramework;
import jakarta.persistence.MappedSuperclass;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

@MappedSuperclass
public abstract class AbstractEntityId<T extends Serializable> implements EntityId<T> {
    private T id;

    @ArtifactForFramework
    protected AbstractEntityId(){
    }
    /* 
     * El constructor vacío se escribe con la notación @ArtifactForFramework para 
     * indicarle que dicho constuctor está aquí por el framework que lo necesita pero 
     * no se usará en la aplicación
     */
    protected AbstractEntityId(T id){
        this.id = Objects.requireNonNull(id);
    }

    @Override
    public T getId(){
        return id;
    }
    @Override 
    public boolean equals(Object o){
        boolean result = false;

        if(this == o){
            result = true;
        } else if (o instanceof AbstractEntityId){
            AbstractEntityId other = (AbstractEntityId) o;
            result = Objects.equals(id, other.id);
        }
        return result;
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
    @Override
    public String toString(){
        return toStringHelper(this)
                .add("id", id)
                .toString();
    }
}
