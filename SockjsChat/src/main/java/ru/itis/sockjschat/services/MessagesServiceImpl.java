package ru.itis.sockjschat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.sockjschat.dto.MessageDto;
import ru.itis.sockjschat.dto.UserDto;
import ru.itis.sockjschat.models.Message;
import ru.itis.sockjschat.models.User;
import ru.itis.sockjschat.repositories.MessagesRepository;

import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesRepository messagesRepository;

    @Autowired
    private UserService userService;

    @Override
    public void saveMessage(MessageDto messageDto) {
        UserDto userDto = userService.findUserByLogin(messageDto.getSender());
        try {
            messagesRepository.save(Message.builder()
                    .roomId(messageDto.getRoomId())
                    .message(messageDto.getText())
                    .user(User.builder().id(userDto.getId()).build())
                    .build());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<MessageDto> getAllMessagesFromRoom() {
        return null;
    }
}
