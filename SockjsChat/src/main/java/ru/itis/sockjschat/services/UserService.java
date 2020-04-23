package ru.itis.sockjschat.services;

import ru.itis.sockjschat.dto.UserDto;

public interface UserService {

    UserDto findUserByLogin(String login);
}
