package ru.itis.semesterwork.dto;

import lombok.*;
import ru.itis.semesterwork.models.Sandbox;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SandboxDto extends Dto {

    private String id;
    private String name;
    private String htmlCode;
    private String jsCode;
    private String cssCode;

    public static SandboxDto from(Sandbox sandbox) {
        System.out.println("Creating sandboxdto from " + sandbox);
        return SandboxDto.builder()
                .cssCode(sandbox.getCssCode())
                .htmlCode(sandbox.getHtmlCode())
                .jsCode(sandbox.getJsCode())
                .id(sandbox.getId())
                .name(sandbox.getName())
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
