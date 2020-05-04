package ru.itis.javalabqueueplain.protocol.sdk;

import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

@NoArgsConstructor
public class JLMQConsumer {
    private String queueName;
    private WebSocketSession socketSession;

    public JLMQConsumer(String queueName, WebSocketSession socketSession) {
        this.queueName = queueName;
        this.socketSession = socketSession;
    }
}
