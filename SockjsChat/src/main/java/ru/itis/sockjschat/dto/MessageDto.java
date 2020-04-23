package ru.itis.sockjschat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.sockjschat.models.Message;
import ru.itis.sockjschat.models.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private String message;
    private User sender;
    private Long roomId;

    public static MessageDto from(Message message) {
        return MessageDto.builder()
                .message(message.getMessage())
                .sender(message.getSender())
                .build();
    }
}
