package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semesterwork.dto.SandboxDto;
import ru.itis.semesterwork.models.Sandbox;
import ru.itis.semesterwork.services.SandboxService;

@RestController
public class SandboxRestController {

    @Autowired
    private SandboxService sandboxService;

    @GetMapping("/api/1.0/sandbox")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getTemplateSandbox() {
        Sandbox sandbox = sandboxService.loadWithRandomId();
        return ResponseEntity.ok(SandboxDto.from(sandbox));
    }

    @PostMapping("/api/1.0/sandbox")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> saveSandbox() {
        Sandbox sandbox = sandboxService.loadWithRandomId();
        return ResponseEntity.ok(SandboxDto.from(sandbox));
    }

 }
