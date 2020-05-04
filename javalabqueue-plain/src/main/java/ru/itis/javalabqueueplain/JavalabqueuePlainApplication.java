package ru.itis.javalabqueueplain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.javalabqueueplain.protocol.messages.TaskMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

@SpringBootApplication
public class JavalabqueuePlainApplication {

    private Map<String, Queue<TaskMessage>> queues = new HashMap<>();
    private Map<String, WebSocketSession> consumers = new HashMap<>();

    @Bean
    public Map<String, Queue<TaskMessage>> queues() {
        return queues;
    }

    @Bean
    public Map<String, WebSocketSession> webSocketSessionMap() {
        return consumers;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(JavalabqueuePlainApplication.class, args);
    }

}
