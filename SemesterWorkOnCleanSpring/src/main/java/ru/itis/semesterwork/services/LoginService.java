package ru.itis.semesterwork.services;

import ru.itis.semesterwork.dto.TokenDto;
import ru.itis.semesterwork.dto.UserDto;
import ru.itis.semesterwork.forms.LoginForm;

public interface LoginService {

    void login(LoginForm loginForm);

    TokenDto apiLogin(LoginForm loginForm);
}
