package ru.itis.sockjschat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.sockjschat.forms.RoomForm;
import ru.itis.sockjschat.models.Room;
import ru.itis.sockjschat.services.RoomsService;

import java.util.List;

@Controller
public class RoomsController {


    @Autowired
    private RoomsService roomsService;

    @GetMapping("/rooms")
    public ModelAndView getRoomsPage() {
        ModelAndView modelAndView = new ModelAndView();
        getAllRooms(modelAndView);
        return modelAndView;
    }

    @PostMapping("/rooms")
    public ModelAndView createRoom(RoomForm roomForm) {
        System.out.println(roomForm);
        ModelAndView modelAndView = new ModelAndView();
        roomsService.addRoom(roomForm.getName());
        getAllRooms(modelAndView);
        return modelAndView;
    }

    @GetMapping("/rooms/{roomId}")
    public ModelAndView getJoinedRoom(@PathVariable Long roomId) {
        ModelAndView modelAndView = new ModelAndView();
        Room room = roomsService.getRoomById(roomId);
        if (room != null) {
            modelAndView.addObject("room", room);
            modelAndView.setViewName("room");
        } else {
            modelAndView.setViewName("redirect:/rooms");
        }
        return modelAndView;
    }

    private void getAllRooms(ModelAndView modelAndView) {
        List<Room> roomList = roomsService.getAllRooms();
        modelAndView.addObject("rooms", roomList);
        modelAndView.setViewName("rooms");
    }
}
