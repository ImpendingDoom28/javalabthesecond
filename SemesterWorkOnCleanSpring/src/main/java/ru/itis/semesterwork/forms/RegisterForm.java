package ru.itis.semesterwork.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @NotNull
    @Size(min = 6, max = 20)
    private String nickname;
    @NotNull
    private String password;
    @NotNull
    private String confirmPassword;

    @NotNull
    @Email
    @Size(min = 8)
    private String email;
}
