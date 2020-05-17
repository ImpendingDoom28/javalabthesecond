package ru.itis.semesterwork.services;

import ru.itis.semesterwork.forms.RegisterForm;

public interface RegisterService {
    //Uses RP to send email
    void register(RegisterForm form);

}
