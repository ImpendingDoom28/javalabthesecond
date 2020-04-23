package ru.itis.sockjschat.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.sockjschat.dto.MessageDto;
import ru.itis.sockjschat.models.Room;
import ru.itis.sockjschat.services.MessagesService;
import ru.itis.sockjschat.services.RoomsService;

@Component
@EnableWebSocket
public class WebSocketsMessagesHandler extends TextWebSocketHandler {

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private RoomsService roomsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String messageResult = (String) message.getPayload();
        MessageDto messageFromJson = objectMapper.readValue(messageResult, MessageDto.class);

        Room room = roomsService.getRoomById(messageFromJson.getRoomId());
        if(room.getClients().containsKey(messageFromJson.getSender())) {
            room.getClients().put(messageFromJson.getSender(), session);
        }
        MessageDto messageDto = MessageDto.builder()
                .sender(messageFromJson.getSender())
                .roomId(messageFromJson.getRoomId())
                .text(messageFromJson.getText())
                .build();

        roomsService.addMessageToRoom(messageFromJson.getRoomId(), messageDto);
        messagesService.saveMessage(messageDto);

        for (WebSocketSession currentSession :room.getClients().values()) {
            try {
                currentSession.sendMessage(message);
            } catch (Exception ex) {
                synchronized (room.getClients()) {
                    room.getClients().remove(messageFromJson.getSender(), currentSession);
                }
            }
        }
    }


}
