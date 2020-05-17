package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.itis.semesterwork.dto.SandboxDto;
import ru.itis.semesterwork.dto.SuccessDto;
import ru.itis.semesterwork.security.jwt.details.UserDetailsImpl;
import ru.itis.semesterwork.services.SandboxService;

@RestController
public class SandboxRestController {

    @Autowired
    private SandboxService sandboxService;

    @GetMapping("/api/1.0/sandbox")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getTemplateSandbox() {
        SandboxDto sandboxDto = sandboxService.loadWithRandomId();
        return ResponseEntity.ok(sandboxDto);
    }

    @PostMapping("/api/1.0/sandbox")
    @CrossOrigin(origins = "http://localhost:3000")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<?> saveSandbox(@RequestBody SandboxDto sandboxDto) {
        System.out.println(sandboxDto);
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getDetails();
        sandboxService.saveOrUpdate(sandboxDto, userDetails.getUser());
        return ResponseEntity.ok(new SuccessDto("Successfully saved sandbox!!!"));
    }

    @GetMapping("/api/1.0/sandbox/{sandbox-id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> loadSandboxById(@PathVariable(name = "sandbox-id") String id) {
        return ResponseEntity.ok(sandboxService.load(id));
    }

 }
