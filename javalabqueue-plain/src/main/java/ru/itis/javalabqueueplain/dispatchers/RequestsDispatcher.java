package ru.itis.javalabqueueplain.dispatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.javalabqueueplain.managers.TaskManager;
import ru.itis.javalabqueueplain.models.Task;
import ru.itis.javalabqueueplain.models.TaskStatus;
import ru.itis.javalabqueueplain.protocol.messages.*;
import ru.itis.javalabqueueplain.services.TaskService;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class RequestsDispatcher {

    @Autowired
    private Map<String, WebSocketSession> consumers;
    @Autowired
    private Map<String, Queue<TaskMessage>> queues;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskManager taskManager;

    public void dispatch(WebSocketMessage<?> message, WebSocketSession socketSession) throws JsonProcessingException {
        String socketMessage = (String) message.getPayload();
        JsonWrapper json = objectMapper.readValue(socketMessage, JsonWrapper.class);
        switch (json.getHeader()) {
            case "subscribe":
                SubscribeMessage subscribeMessage = objectMapper.readValue(socketMessage, SubscribeMessageJsonWrapper.class).getPayload();
                consumers.put(subscribeMessage.getQueueName(), socketSession);
                if (queues.containsKey(subscribeMessage.getQueueName()) && !queues.get(subscribeMessage.getQueueName()).isEmpty()) {
                    taskManager.sendTask(subscribeMessage.getQueueName());
                }
                break;
            case "send":
                TaskMessage taskMessage = objectMapper.readValue(socketMessage, TaskMessageJsonWrapper.class).getPayload();
                taskMessage.setCommand("receive");
                taskMessage.setMessageId(UUID.randomUUID().toString());
                taskService.save(Task.builder()
                        .queueName(taskMessage.getQueueName())
                        .taskId(taskMessage.getMessageId())
                        .status(TaskStatus.CREATED)
                        .createdAt(LocalDateTime.now())
                        .build());
                if(queues.get(taskMessage.getQueueName()) == null) {
                    queues.put(taskMessage.getQueueName(), new ArrayDeque<>());
                }
                queues.get(taskMessage.getQueueName()).add(taskMessage);
                System.out.println(queues.toString());
                if (consumers.containsKey(taskMessage.getQueueName())) {
                    taskManager.sendTask(taskMessage.getQueueName());
                }
                break;

            case "accepted":
                TaskMessage acceptedTaskMessage = objectMapper.readValue(socketMessage, TaskMessageJsonWrapper.class).getPayload();
                Task task = taskService.getByTaskId(acceptedTaskMessage.getMessageId());
                task.setStatus(TaskStatus.ACCEPTED);
                task.setCreatedAt(LocalDateTime.now());
                taskService.save(task);
                break;

            case "completed":
                TaskMessage completeTaskMessage = objectMapper.readValue(socketMessage, TaskMessageJsonWrapper.class).getPayload();
                Task completeTask = taskService.getByTaskId(completeTaskMessage.getMessageId());
                completeTask.setStatus(TaskStatus.COMPLETED);
                completeTask.setCreatedAt(LocalDateTime.now());
                taskService.save(completeTask);
                if (!queues.get(completeTask.getQueueName()).isEmpty()) {
                    taskManager.sendTask(completeTask.getQueueName());
                }
                break;
        }
    }
}
