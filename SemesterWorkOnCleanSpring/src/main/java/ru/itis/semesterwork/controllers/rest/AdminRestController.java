package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semesterwork.dto.UsersDto;
import ru.itis.semesterwork.services.UserService;

@RestController
public class AdminRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/1.0/admin/users")
    @CrossOrigin(origins = {"http://localhost:3000"})
    public ResponseEntity<?> getAllUsers() {
        UsersDto usersDto = UsersDto.builder()
                .users(userService.getAllUsers())
                .build();
        return ResponseEntity.ok(usersDto);
    }
}
