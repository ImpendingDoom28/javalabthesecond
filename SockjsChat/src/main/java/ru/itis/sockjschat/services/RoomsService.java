package ru.itis.sockjschat.services;

import ru.itis.sockjschat.models.Room;

import java.util.List;

public interface RoomsService {

    List<Room> getAllRooms();

    Room addRoom(String name);

    Room getRoomById(Long roomId);

    Long generateId();

}
