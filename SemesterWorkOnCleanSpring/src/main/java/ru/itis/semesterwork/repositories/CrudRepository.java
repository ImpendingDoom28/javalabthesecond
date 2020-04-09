package ru.itis.semesterwork.repositories;

import java.util.Optional;

public interface CrudRepository<T, ID> {

    Optional<T> save(T entity);

    Optional<T> delete(T entity);

    Optional<T> find(T entity);

    Optional<T> findById(ID id);

    Optional<T> update(T entity);
}
