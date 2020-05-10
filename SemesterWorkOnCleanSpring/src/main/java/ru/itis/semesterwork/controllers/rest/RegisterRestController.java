package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import ru.itis.semesterwork.dto.ErrorDto;
import ru.itis.semesterwork.dto.SignUpDto;
import ru.itis.semesterwork.events.OnRegisterSuccessEvent;
import ru.itis.semesterwork.forms.UserForm;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.services.RegisterService;
import ru.itis.semesterwork.services.UserService;

@RestController
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class RegisterRestController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/api/1.0/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> handleRegister(@RequestBody UserForm userForm, WebRequest webRequest) {
        try {
            registerService.register(userForm);
            String appUrl = webRequest.getContextPath();
            User user = userService.findByNickname(userForm.getNickname());
            eventPublisher.publishEvent(new OnRegisterSuccessEvent(user, appUrl));
            return ResponseEntity.ok(SignUpDto.builder()
                    .message("You successfully registered! Check your email box to confirm your account")
                    .build());
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.ok(new ErrorDto(e.getMessage()));
        }
    }

}
