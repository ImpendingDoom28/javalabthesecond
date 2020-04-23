package ru.itis.sockjschat.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.sockjschat.repositories.MessagesRepository;
import ru.itis.sockjschat.services.RoomsService;

@Component
@EnableWebSocket
public class WebSocketsMessagesHandler extends TextWebSocketHandler {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private RoomsService roomsService;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        //TODO deserialize message to get roomId;
        Long roomId = 1L;
        roomsService.getRoomById(roomId);
//        messagesRepository.save(Message.builder()
//                .roomId()
//                .build());
        session.sendMessage(message);
    }


}
