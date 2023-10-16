package no.noroff.alumni.services;

import java.util.Collection;

public interface CRUDService <T, ID>{
    T findById(ID id);
    Collection<T> findAll();
    T add(T entity);
    T update(T entity);
    void deleteById(ID id);
    boolean exists (ID id);
}
