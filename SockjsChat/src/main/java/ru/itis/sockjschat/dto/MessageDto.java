package ru.itis.sockjschat.dto;

import lombok.Data;

@Data
public class MessageDto {

    private String text;
    private String sender;
    private Long roomId;

}
