package ru.itis.semesterwork.dto;

import lombok.Data;
import ru.itis.semesterwork.models.Profile;

@Data
public class ProfileDto {

    private UserDto userInfo;

    //TODO: FIX
    public static ProfileDto from(Profile profile) {
        return null;
    }

}
