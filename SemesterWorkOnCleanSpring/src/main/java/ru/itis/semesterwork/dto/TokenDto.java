package ru.itis.semesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto extends Dto {

    private String token;

    @Override
    public Long getCode() {
        return 0L;
    }

    @Override
    public Boolean getIsError() {
        return false;
    }
}
