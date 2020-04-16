package ru.itis.semesterwork.services;

import ru.itis.semesterwork.dto.MessageDto;

import java.util.List;

public interface MessageService {

    MessageDto saveMessage(MessageDto messageDto);

    List<MessageDto> findAllMessages();
}
