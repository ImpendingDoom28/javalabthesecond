package ru.itis.semesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto extends Dto {

    private String message;

    @Override
    public Long getCode() {
        return 1L;
    }

    @Override
    public Boolean getIsError() {
        return true;
    }
}
