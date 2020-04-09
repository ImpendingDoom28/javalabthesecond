package ru.itis.semesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semesterwork.models.Sandbox;

@Data
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
        return SandboxDto.builder()
                .cssCode(sandbox.getCssCode())
                .htmlCode(sandbox.getHtmlCode())
                .jsCode(sandbox.getJsCode())
                .id(sandbox.getId())
                .name(sandbox.getName())
                .build();
    }

}
