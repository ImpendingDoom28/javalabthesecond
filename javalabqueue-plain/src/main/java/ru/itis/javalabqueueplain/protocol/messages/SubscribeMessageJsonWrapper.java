package ru.itis.javalabqueueplain.protocol.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeMessageJsonWrapper {
    String header;
    SubscribeMessage payload;
}
