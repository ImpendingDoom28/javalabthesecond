package ru.itis.sockjschat.repositories;

import java.util.Optional;

public interface CrudRepository<T, ID> {

    Optional<T> save(T t);

    Optional<T> find(T t);

    Optional<T> findById(ID id);

    Optional<T> delete(T t);

    Optional<T> update(T t);

}
