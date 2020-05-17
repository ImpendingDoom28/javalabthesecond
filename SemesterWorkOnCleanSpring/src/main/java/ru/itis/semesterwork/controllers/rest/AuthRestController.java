package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semesterwork.dto.Dto;
import ru.itis.semesterwork.dto.ErrorDto;
import ru.itis.semesterwork.services.TokenService;


@RestController
public class AuthRestController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/api/1.0/auth")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<?> verifyVerificationToken(@RequestParam String verificationToken) {
        try {
            Dto dto = new Dto();
            dto.setIsError(tokenService.validateVerificationToken(verificationToken));
            return ResponseEntity.ok(dto);
        } catch(IllegalArgumentException e) {
            return ResponseEntity.ok(new ErrorDto(e.getMessage()));
        }
    }
}
