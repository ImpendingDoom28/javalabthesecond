package ru.itis.springemail.repositories;

import java.util.Optional;

public interface CrudRepository<T, ID> {

    Optional<T> save(T model);

    Optional<T> find(T model);

    Optional<T> findById(ID id);

    void update(T model);

    void delete(T model);

}
