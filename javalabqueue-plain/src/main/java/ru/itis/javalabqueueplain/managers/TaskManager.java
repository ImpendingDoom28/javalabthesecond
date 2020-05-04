package ru.itis.javalabqueueplain.managers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jmx.remote.internal.ArrayQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.javalabqueueplain.protocol.messages.JsonWrapper;
import ru.itis.javalabqueueplain.protocol.messages.TaskMessage;

import java.io.IOException;
import java.util.Map;
import java.util.Queue;

@Component
public class TaskManager {

    @Autowired
    private Map<String, WebSocketSession> consumers;

    @Autowired
    private Map<String, Queue<TaskMessage>> queues;

    @Autowired
    private ObjectMapper mapper;

    private void prepareAndSendTask(String queueName) throws IOException {

        String message = mapper.writeValueAsString(JsonWrapper.builder()
                .header("receive")
                .payload(queues.get(queueName).poll())
                .build());

        TextMessage textMessage = new TextMessage(message);
        System.out.println("Sending task: " + message);
        consumers.get(queueName).sendMessage(textMessage);
    }

    public void sendTask(String queueName) {
        try {
            prepareAndSendTask(queueName);
        } catch (IOException e) {
            System.out.println();
            throw new IllegalArgumentException(e);
        }
    }
}
