package ru.itis.javalabqueueplain.protocol.sdk;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import ru.itis.javalabqueueplain.protocol.messages.JsonWrapper;
import ru.itis.javalabqueueplain.protocol.messages.SubscribeMessage;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;

@AllArgsConstructor
public class JLMQConnector {
    private static String address;
    private static WebSocketClient webSocketClient;

    public JLMQConnector(String address, WebSocketClient webSocketClient) {
        JLMQConnector.address = address;
        JLMQConnector.webSocketClient = webSocketClient;
    }

    public JLMQProducerBuilder producer() {
        return new JLMQProducerBuilder();
    }

    public JLMQConsumerBuilder consumer() {
        return new JLMQConsumerBuilder();
    }


    public static class JLMQProducerBuilder {
        private String queueName;
        private WebSocketHandler webSocketHandler;

        public JLMQProducerBuilder toQueue(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public JLMQProducerBuilder onReceive(WebSocketHandler webSocketHandler) {
            this.webSocketHandler = webSocketHandler;
            return this;
        }

        public JLMQProducer create() {
            try {
                WebSocketSession socketSession = webSocketClient.doHandshake(webSocketHandler, new WebSocketHttpHeaders(), URI.create(address)).get();
                return new JLMQProducer(queueName, socketSession);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println();
                throw new IllegalArgumentException(e);
            }
        }
    }

    public static class JLMQConsumerBuilder {
        private String queueName;
        private WebSocketHandler webSocketHandler;

        public JLMQConsumerBuilder subscribe(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public JLMQConsumerBuilder onReceive(WebSocketHandler webSocketHandler) {
            this.webSocketHandler = webSocketHandler;
            return this;
        }

        public JLMQConsumer create() {
            try {
                WebSocketSession socketSession = webSocketClient.doHandshake(webSocketHandler,
                        new WebSocketHttpHeaders(), URI.create(address)).get();

                SubscribeMessage subscribeMessage = SubscribeMessage.builder()
                        .queueName(queueName)
                        .build();

                JsonWrapper json = JsonWrapper.builder()
                        .header("subscribe")
                        .payload(subscribeMessage)
                        .build();

                ObjectMapper objectMapper = new ObjectMapper();
                socketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(json)));
                return new JLMQConsumer(queueName, socketSession);
            } catch (InterruptedException | ExecutionException | IOException e) {
                System.out.println();
                throw new IllegalArgumentException(e);
            }
        }
    }


}
