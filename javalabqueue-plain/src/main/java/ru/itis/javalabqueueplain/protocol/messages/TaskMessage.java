package ru.itis.javalabqueueplain.protocol.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskMessage {
    String command;
    String queueName;
    String messageId;
    Object body;
}
