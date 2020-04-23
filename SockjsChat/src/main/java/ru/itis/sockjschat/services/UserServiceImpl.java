package ru.itis.sockjschat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.sockjschat.dto.UserDto;
import ru.itis.sockjschat.repositories.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDto findUserByLogin(String login) {
        return UserDto.from(usersRepository.findByLogin(login).get());
    }
}
