package ru.itis.semesterwork.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.semesterwork.dto.ErrorDto;
import ru.itis.semesterwork.dto.TokenDto;
import ru.itis.semesterwork.dto.UserDto;
import ru.itis.semesterwork.forms.LoginForm;
import ru.itis.semesterwork.services.LoginService;
import ru.itis.semesterwork.services.TokenService;

@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/api/1.0/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginForm loginForm){
        try {
            TokenDto tokenDto = loginService.apiLogin(loginForm);
            return ResponseEntity.ok(tokenDto);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.ok(new ErrorDto(e.getLocalizedMessage()));
        }
    }

}
