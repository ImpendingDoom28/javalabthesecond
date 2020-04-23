package ru.itis.sockjschat.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.sockjschat.dto.MessageDto;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {

    private Long id;
    private String name;
    private Map<Long, WebSocketSession> clients;
    private List<MessageDto> messages;

}