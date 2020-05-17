package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;
import ru.itis.semesterwork.dto.SuccessDto;
import ru.itis.semesterwork.events.OnRegisterSuccessEvent;
import ru.itis.semesterwork.forms.RegisterForm;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.services.RegisterService;
import ru.itis.semesterwork.services.UserService;

import javax.validation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
@Validated
public class RegisterRestController {

    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/api/1.0/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> handleRegister(@Valid @RequestBody RegisterForm registerForm, WebRequest webRequest) {
        registerService.register(registerForm);
        User user = userService.findByNickname(registerForm.getNickname());
        String appUrl = webRequest.getContextPath();
        eventPublisher.publishEvent(new OnRegisterSuccessEvent(user, appUrl));
        return ResponseEntity.ok(SuccessDto.builder()
                .message("You successfully registered! Check your email box to confirm your account")
                .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
