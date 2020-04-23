package ru.itis.sockjschat.services;

import ru.itis.sockjschat.dto.MessageDto;

import java.util.List;

public interface MessagesService {

    void saveMessage(MessageDto messageDto);

    List<MessageDto> getAllMessagesFromRoom();
}
