package ru.itis.semesterwork.forms;

import lombok.Data;

@Data
public class UserForm {
    private String nickname;
    private String password;
    private String confirmPassword;
    private String email;
}
