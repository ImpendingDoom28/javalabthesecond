package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semesterwork.services.UserProfileService;

@RestController
public class ProfileRestController {

    @GetMapping("/api/1.0/profile/{user-id}")
    public ResponseEntity<?> getProfile(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(null);
    }
}
