package ru.itis.semesterwork.controllers.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import ru.itis.semesterwork.dto.UserRestDataDto;

import java.util.UUID;

@RestController
@RequestMapping("/api/1.0/support")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class SupportChatRestController {

    @GetMapping
    @CrossOrigin(origins = {"http://localhost:3000"})
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getPageId() {
        String pageId = UUID.randomUUID().toString();
        return ResponseEntity.ok(new UserRestDataDto(pageId, null));
    }
}
