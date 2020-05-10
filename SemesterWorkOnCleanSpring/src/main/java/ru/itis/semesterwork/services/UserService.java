package ru.itis.semesterwork.services;

import ru.itis.semesterwork.dto.UserDto;
import ru.itis.semesterwork.models.User;

import java.util.List;

public interface UserService {

    User findByNickname(String nickname);

    User findById(Long userId);

    List<UserDto> getAllUsers();
}
