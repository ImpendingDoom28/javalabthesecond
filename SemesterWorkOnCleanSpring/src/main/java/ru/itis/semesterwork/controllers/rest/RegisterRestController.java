package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semesterwork.dto.ErrorDto;
import ru.itis.semesterwork.dto.SignUpDto;
import ru.itis.semesterwork.forms.UserForm;
import ru.itis.semesterwork.services.RegisterService;

@RestController
public class RegisterRestController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/api/1.0/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> handleRegister(@RequestBody UserForm userForm) {
        try {
            registerService.register(userForm);
            return ResponseEntity.ok(SignUpDto.builder()
                    .message("You successfully registered! Check your email box to confirm your account")
                    .build());
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.ok(new ErrorDto(e.getMessage()));
        }
    }

}
