package ru.itis.semesterwork.repositories;

import ru.itis.semesterwork.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User,Long> {
    Optional<User> findUserByNickname(String nickname);

    List<User> findAll();
}
