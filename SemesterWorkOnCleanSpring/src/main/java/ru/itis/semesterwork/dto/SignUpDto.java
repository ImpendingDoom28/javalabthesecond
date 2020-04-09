package ru.itis.semesterwork.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto extends Dto {

    private String message;

    @Override
    public Long getCode() {
        return 0L;
    }

    @Override
    public Boolean getIsError() {
        return false;
    }
}
