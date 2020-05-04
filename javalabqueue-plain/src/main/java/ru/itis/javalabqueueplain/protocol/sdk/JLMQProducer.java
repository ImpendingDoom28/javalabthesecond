package ru.itis.javalabqueueplain.protocol.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.javalabqueueplain.protocol.messages.JsonWrapper;

import java.io.IOException;

@NoArgsConstructor
public class JLMQProducer {
    private String queueName;
    private WebSocketSession socketSession;
    private ObjectMapper objectMapper;

    public JLMQProducer(String queueName, WebSocketSession socketSession) {
        this.queueName = queueName;
        this.socketSession = socketSession;
        objectMapper = new ObjectMapper();
    }

    public void send(JsonWrapper json) {
        try {
            socketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(json)));
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }
}
