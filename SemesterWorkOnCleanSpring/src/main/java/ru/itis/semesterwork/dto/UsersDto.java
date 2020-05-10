package ru.itis.semesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto extends Dto {

    private List<UserDto> users;

    @Override
    public Long getCode() {
        return 0L;
    }

    @Override
    public Boolean getIsError() {
        return false;
    }
}
