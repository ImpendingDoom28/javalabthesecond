package ru.itis.sockjschat.repositories;

import ru.itis.sockjschat.models.User;

import java.util.Optional;

public interface UsersRepository extends  CrudRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
