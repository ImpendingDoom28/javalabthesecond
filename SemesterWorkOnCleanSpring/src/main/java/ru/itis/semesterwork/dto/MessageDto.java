package ru.itis.semesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.semesterwork.models.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto extends Dto{

    private String pageId;
    private String message;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .message(message.getMessage())
                .pageId(message.getPageId())
                .build();
    }

    @Override
    public Long getCode() {
        return 0L;
    }

    @Override
    public Boolean getIsError() {
        return false;
    }
}
