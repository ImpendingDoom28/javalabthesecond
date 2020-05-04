package ru.itis.javalabqueueplain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalabqueueplain.protocol.messages.TaskMessage;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

@Controller
public class QueueController {

    @Autowired
    private Map<String, Queue<TaskMessage>> queues;

    @GetMapping("/queue")
    public String getQueuePage() {
        return "queue_page";
    }

    @PostMapping("/queue")
    public String createQueue(@RequestParam("queueName") String queueName) {
        queues.put(queueName, new ArrayDeque<>());
        return "consumer_page";
    }
}
