package ru.itis.javalabqueueplain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketHandler;
import ru.itis.javalabqueueplain.protocol.sdk.JLMQ;
import ru.itis.javalabqueueplain.protocol.sdk.JLMQConnector;
import ru.itis.javalabqueueplain.protocol.sdk.JLMQConsumer;

@Controller
public class ConsumersController {

    @Autowired
    @Qualifier("jlmqWebSocketHandler")
    WebSocketHandler webSocketHandler;

    @GetMapping("/consumer")
    public ModelAndView getConsumerPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("consumer_page");
        return modelAndView;
    }

    @PostMapping("/consumer")
    public ModelAndView createConsumer(@RequestParam("queueName") String queueName) {
        ModelAndView modelAndView = new ModelAndView();
        JLMQConnector connector = JLMQ.connector()
                .address("ws://localhost:8080/jlmq")
                .connect();
        JLMQConsumer consumer = connector.consumer()
                .subscribe(queueName)
                .onReceive(webSocketHandler)
                .create();
        modelAndView.setViewName("redirect:/consumer");
        return modelAndView;
    }
}
