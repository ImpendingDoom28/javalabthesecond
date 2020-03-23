package ru.itis.mail.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, V> {
    Optional<V> find(ID id);
    List<V> findAll();
    Optional<V> save(V entity);
    void delete(ID id);
}
