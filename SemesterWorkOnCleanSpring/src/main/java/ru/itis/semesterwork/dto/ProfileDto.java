package ru.itis.semesterwork.dto;

import lombok.*;
import ru.itis.semesterwork.models.Profile;
import ru.itis.semesterwork.models.Sandbox;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto extends Dto{

    private String bio;
    private List<SandboxDto> sandboxes;

    public static ProfileDto from(Profile profile) {
        System.out.println("Creating profiledto from " + profile);
        return ProfileDto.builder()
                .bio(profile.getBio())
                .sandboxes(profile.getUser().getSandboxList()
                        .stream()
                        .map((sandbox -> SandboxDto.from(sandbox)))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Boolean getIsError() {
        return false;
    }

    @Override
    public Long getCode() {
        return 0L;
    }
}
