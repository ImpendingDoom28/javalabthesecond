package ru.itis.semesterwork.services;

import ru.itis.semesterwork.models.User;

public interface UserService {

    User findByNickname(String nickname);

    User findById(Long userId);
}
