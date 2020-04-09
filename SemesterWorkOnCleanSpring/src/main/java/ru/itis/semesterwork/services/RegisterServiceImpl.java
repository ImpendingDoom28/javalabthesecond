package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.dto.SignUpDto;
import ru.itis.semesterwork.dto.UserDto;
import ru.itis.semesterwork.forms.UserForm;
import ru.itis.semesterwork.models.Role;
import ru.itis.semesterwork.models.State;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(UserForm form) {
        if(form.getPassword().equals(form.getConfirmPassword())) {
            Optional<User> userCheckOptional = usersRepository.findUserByNickname(form.getNickname());
            if(!userCheckOptional.isPresent()) {
                User userToSave = User.builder()
                        .email(form.getEmail())
                        .hashPassword(passwordEncoder.encode(form.getPassword()))
                        .nickname(form.getNickname())
                        .role(Role.USER)
                        .state(State.NOT_CONFIRMED)
                        .createdAt(LocalDateTime.now())
                        .build();
                Optional<User> userOptional = usersRepository.save(userToSave);
                if(!userOptional.isPresent()) throw new IllegalStateException("No user is present, unexpected error with database");
            } else throw new IllegalArgumentException("User with given nickname exist!");
        } else throw new IllegalArgumentException("Confirm password and password are not equal");
    }
}
