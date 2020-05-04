package ru.itis.javalabqueueplain.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.javalabqueueplain.protocol.messages.ActionMessage;
import ru.itis.javalabqueueplain.protocol.messages.JsonWrapper;
import ru.itis.javalabqueueplain.protocol.messages.TaskMessage;
import ru.itis.javalabqueueplain.protocol.messages.TaskMessageJsonWrapper;

@Component("jlmqWebSocketHandler")
@EnableWebSocket
public class WebSocketConsumerMessageHandler extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String socketMessage = (String) message.getPayload();
        JsonWrapper jsonMessage = mapper.readValue(socketMessage, JsonWrapper.class);
        switch (jsonMessage.getHeader()) {
            case "receive" :
                JsonWrapper messageToSend;
                TaskMessageJsonWrapper jsonTaskMessage = mapper.readValue(socketMessage, TaskMessageJsonWrapper.class);
                TaskMessage taskMessage = jsonTaskMessage.getPayload();

                ActionMessage acceptedMessage = ActionMessage.builder()
                        .command("accepted")
                        .messageId(taskMessage.getMessageId())
                        .build();
                messageToSend = JsonWrapper.builder()
                        .header("accepted")
                        .payload(acceptedMessage)
                        .build();
                session.sendMessage(new TextMessage(mapper.writeValueAsString(messageToSend)));

                System.out.println("Received task");
                System.out.println(taskMessage.toString());

                ActionMessage completedMessage = ActionMessage.builder()
                        .command("completed")
                        .messageId(taskMessage.getMessageId())
                        .build();
                messageToSend = JsonWrapper.builder()
                        .header("completed")
                        .payload(completedMessage)
                        .build();
                session.sendMessage(new TextMessage(mapper.writeValueAsString(messageToSend)));

                break;
        }
    }
}
