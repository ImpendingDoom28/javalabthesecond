package ru.itis.javalabqueueplain.protocol.sdk;

import org.springframework.web.socket.client.standard.StandardWebSocketClient;

public class JLMQ {

    public static Connector connector() {
        return new Connector();
    }

    public static class Connector {
        private String address;

        public Connector address(String address) {
            this.address = address;
            return this;
        }

        public JLMQConnector connect() {
            return new JLMQConnector(address, new StandardWebSocketClient());
        }
    }
}
