package ru.itis.sockjschat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.sockjschat.dto.MessageDto;
import ru.itis.sockjschat.models.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoomsServiceImpl implements RoomsService {

    private static final List<Room> rooms = new ArrayList<>();

    @Autowired
    private MessagesService messagesService;

    @Override
    public List<Room> getAllRooms() {
        return rooms;
    }

    @Override
    public Room addRoom(String name) {
        Long roomId = Long.parseLong("" + (rooms.size() + 1));
        Room room = Room.builder()
                .name(name)
                .id(roomId)
                .clients(new HashMap<>())
                .messages(new ArrayList<>())
                .build();
        rooms.add(room);
        return room;
    }

    @Override
    public Room getRoomById(Long roomId) {
        for (Room room: rooms) {
            if(room.getId().equals(roomId)) {
                System.out.println("Founded room: " + room);
//                room.setMessages();
                return room;
            }
        }
        return null;
    }

    @Override
    public Long generateId() {
        return (long) rooms.size();
    }

    @Override
    public void addMessageToRoom(Long roomId, MessageDto message) {
        for(Room room: rooms) {
            if(room.getId().equals(roomId)) {
                room.getMessages().add(message);
                break;
            }
        }
    }
}