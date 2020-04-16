package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.dto.MessageDto;
import ru.itis.semesterwork.models.Message;
import ru.itis.semesterwork.repositories.MessageRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public MessageDto saveMessage(MessageDto messageDto) {
        if (!messageDto.getMessage().equals("SupportChatInit")) {
            Message message = Message.builder()
                    .message(messageDto.getMessage())
                    .createdAt(LocalDateTime.now())
                    .pageId(messageDto.getPageId())
                    .build();
            Optional<Message> returnedMessage = messageRepository.save(message);
            if(returnedMessage.isPresent()) {
                return MessageDto.from(returnedMessage.get());
            } else throw new IllegalStateException("Something went wrong with database! Check it, quick!");
        } else {
            return MessageDto.builder()
                    .pageId("")
                    .message("")
                    .build();
        }
    }

    @Override
    public List<MessageDto> findAllMessages() {
        List<Message> messageList = messageRepository.findAll();
        List<MessageDto> messageDtos = new ArrayList<>();
        for(Message message: messageList) {
            messageDtos.add(MessageDto.from(message));
        }
        return messageDtos;
    }
}
