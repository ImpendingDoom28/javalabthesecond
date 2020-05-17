package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import ru.itis.semesterwork.dto.ErrorDto;
import ru.itis.semesterwork.dto.UserRestDataDto;
import ru.itis.semesterwork.forms.FacebookLoginForm;
import ru.itis.semesterwork.forms.LoginForm;
import ru.itis.semesterwork.services.LoginService;

@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/api/1.0/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginForm loginForm){
        try {
            UserRestDataDto userRestDataDto = loginService.apiLogin(loginForm);
            return ResponseEntity.ok(userRestDataDto);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok(new ErrorDto(e.getLocalizedMessage()));
        }
    }

    @PostMapping("/api/1.0/facebook/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createAuthenticationTokenFromFacebook(@RequestBody FacebookLoginForm facebookLoginForm){
        try {
            UserRestDataDto userRestDataDto = loginService.facebookApiLogin(facebookLoginForm);
            return ResponseEntity.ok(userRestDataDto);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok(new ErrorDto(e.getLocalizedMessage()));
        }
    }

}
