package ru.itis.sockjschat.services;

import org.springframework.stereotype.Service;
import ru.itis.sockjschat.models.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class RoomsServiceImpl implements RoomsService {

    private static final List<Room> rooms = new ArrayList<>();

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
                return room;
            }
        }
        return null;
    }

    @Override
    public Long generateId() {
        return (long) rooms.size();
    }
}