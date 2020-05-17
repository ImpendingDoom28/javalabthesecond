package ru.itis.semesterwork.forms;

import lombok.Data;

@Data
public class FacebookLoginForm {
    private String email;
    private String nickname;
    private String accessToken;
}
