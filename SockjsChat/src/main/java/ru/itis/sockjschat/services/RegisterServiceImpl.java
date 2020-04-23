package ru.itis.sockjschat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.sockjschat.forms.RegisterForm;
import ru.itis.sockjschat.models.User;
import ru.itis.sockjschat.repositories.UsersRepository;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void register(RegisterForm form) {
        if(!form.getConfirmPassword().equals(form.getPassword())) {
            throw new IllegalArgumentException("Passwords are not equal!!!");
        }
        if(usersRepository.findByLogin(form.getLogin()).isPresent()) {
            throw new IllegalArgumentException("User with this login already exist!");
        }
        usersRepository.save(User.builder()
                .hashPassword(securityService.hashPassword(form.getPassword()))
                .login(form.getLogin())
                .build());
    }
}
