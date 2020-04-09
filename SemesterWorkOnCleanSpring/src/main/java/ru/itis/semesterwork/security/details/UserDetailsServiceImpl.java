package ru.itis.semesterwork.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.repositories.UsersRepository;

import java.util.Optional;

@Service(value = "customUserDetails")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        Optional<User> userOptional = usersRepository.findUserByNickname(nickname);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDetailsImpl(user);
        } throw new UsernameNotFoundException("User with nickname " + nickname + " not found");
    }
}
