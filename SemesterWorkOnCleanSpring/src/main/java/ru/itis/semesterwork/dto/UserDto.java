package ru.itis.semesterwork.dto;

import lombok.*;
import ru.itis.semesterwork.models.Role;
import ru.itis.semesterwork.models.State;
import ru.itis.semesterwork.models.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String nickname;
    private String email;
    private Role role;
    private State state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .role(user.getRole())
                .state(user.getState())
                .id(user.getId())
                .build();
    }
}
