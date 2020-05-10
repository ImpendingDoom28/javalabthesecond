package ru.itis.semesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRestDataDto extends Dto {

    private String token;
    //specially for React front
    private UserDto userData;

    @Override
    public Long getCode() {
        return 0L;
    }

    @Override
    public Boolean getIsError() {
        return false;
    }
}
