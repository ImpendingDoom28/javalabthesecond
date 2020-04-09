package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User findByNickname(String nickname) {
        Optional<User> userOptional = usersRepository.findUserByNickname(nickname);
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else throw new IllegalArgumentException("No such user");
    }

    @Override
    public User findById(Long userId) {
        Optional<User> userOptional = usersRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else throw new IllegalArgumentException("No such user");
    }
}
