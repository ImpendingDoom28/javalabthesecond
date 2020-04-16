package ru.itis.semesterwork.controllers.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semesterwork.dto.TokenDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/1.0/support")
public class SupportChatRestController {

    @GetMapping
    @CrossOrigin(origins = {"http://localhost:3000"})
    public ResponseEntity<?> getPageId() {
        String pageId = UUID.randomUUID().toString();
        return ResponseEntity.ok(new TokenDto(pageId));
    }
}
