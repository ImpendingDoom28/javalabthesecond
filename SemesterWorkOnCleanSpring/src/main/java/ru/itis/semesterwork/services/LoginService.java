package ru.itis.semesterwork.services;

import ru.itis.semesterwork.dto.UserRestDataDto;
import ru.itis.semesterwork.forms.LoginForm;

public interface LoginService {

    void login(LoginForm loginForm);

    UserRestDataDto apiLogin(LoginForm loginForm);
}
