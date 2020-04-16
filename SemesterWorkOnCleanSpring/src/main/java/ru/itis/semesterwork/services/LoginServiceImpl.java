package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.dto.TokenDto;
import ru.itis.semesterwork.forms.LoginForm;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.repositories.UsersRepository;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    @Qualifier("customUserDetails")
    private UserDetailsService userDetailsService;

    @Override
    public void login(LoginForm loginForm) {
        Optional<User> foundUser = usersRepository.findUserByNickname(loginForm.getNickname());
        if(foundUser.isPresent() && passwordEncoder.matches(loginForm.getPassword(), foundUser.get().getHashPassword())) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginForm.getNickname(), loginForm.getPassword())
                );
            } catch (BadCredentialsException e) {
                throw new IllegalArgumentException("Incorrect username or password", e);
            }
        } else throw new IllegalArgumentException("Invalid login or password");
    }

    @Override
    public TokenDto apiLogin(LoginForm loginForm) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginForm.getNickname(), loginForm.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new IllegalArgumentException("Incorrect username or password", e);
//        }
//        UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getNickname());
//        String token = tokenService.generateToken(userDetails);
//        return new TokenDto(token);
        return null;
    }
}
