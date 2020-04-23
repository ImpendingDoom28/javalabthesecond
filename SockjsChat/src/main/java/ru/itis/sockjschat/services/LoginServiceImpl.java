package ru.itis.sockjschat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.sockjschat.forms.LoginForm;
import ru.itis.sockjschat.models.User;
import ru.itis.sockjschat.repositories.UsersRepository;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void login(LoginForm loginForm) {
        Optional<User> userOptional = usersRepository.findByLogin(loginForm.getLogin());
        System.out.println(userOptional.isPresent());
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            String pass = user.getHashPassword();
            if(!securityService.compare(loginForm.getPassword(), pass)) {
                throw new IllegalArgumentException("Illegal Password or Login");
            }
        } else throw new IllegalArgumentException("No such user found");
    }
}
