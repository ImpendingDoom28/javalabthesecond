package ru.itis.semesterwork.services;

import ru.itis.semesterwork.forms.UserForm;

public interface RegisterService {
    //Uses aspect to send email
    void register(UserForm form);

}
