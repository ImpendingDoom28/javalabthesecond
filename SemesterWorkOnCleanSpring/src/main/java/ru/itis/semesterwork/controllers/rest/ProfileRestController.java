package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semesterwork.dto.ProfileDto;
import ru.itis.semesterwork.models.Profile;
import ru.itis.semesterwork.services.ProfileService;

@RestController
public class ProfileRestController {


    @Autowired
    private ProfileService profileService;

    @GetMapping("/api/1.0/profile/{user-id}")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getProfile(@PathVariable("user-id") Long userId) {
        ProfileDto profileDto = profileService.getUserProfile(userId);
        return ResponseEntity.ok(profileDto);
    }
}
