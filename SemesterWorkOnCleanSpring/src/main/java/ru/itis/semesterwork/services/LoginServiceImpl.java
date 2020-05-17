package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.dto.UserDto;
import ru.itis.semesterwork.dto.UserRestDataDto;
import ru.itis.semesterwork.forms.FacebookLoginForm;
import ru.itis.semesterwork.forms.LoginForm;
import ru.itis.semesterwork.models.Profile;
import ru.itis.semesterwork.models.Role;
import ru.itis.semesterwork.models.State;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.repositories.ProfileRepository;
import ru.itis.semesterwork.repositories.UsersRepository;
import ru.itis.semesterwork.security.jwt.authentication.JwtAuthentication;
import ru.itis.semesterwork.security.jwt.details.UserDetailsImpl;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TokenService tokenService;
    @Autowired
    @Qualifier("myProfileRepository")
    private ProfileRepository profileRepository;
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
    public UserRestDataDto apiLogin(LoginForm loginForm) {
        Optional<User> foundUser = usersRepository.findUserByNickname(loginForm.getNickname());
        if(foundUser.isPresent() && passwordEncoder.matches(loginForm.getPassword(), foundUser.get().getHashPassword())) {
            try {
                UserDetailsImpl userDetails = (UserDetailsImpl)userDetailsService.loadUserByUsername(loginForm.getNickname());
                String token = tokenService.generateToken(userDetails);
                UserDto userDto = UserDto.from(userDetails.getUser());
                if(!profileRepository.findProfileByUserId(userDetails.getUser().getId()).isPresent()) {
                    profileRepository.save(Profile.builder()
                            .bio("Your biography")
                            .user(userDetails.getUser())
                            .build());
                }
                return new UserRestDataDto(token, userDto);
            } catch (BadCredentialsException e) {
                throw new IllegalArgumentException("Incorrect username or password", e);
            }
        } else throw new IllegalArgumentException("Invalid login or password");
    }

    @Override
    public UserRestDataDto facebookApiLogin(FacebookLoginForm facebookLoginForm) {
        Optional<User> foundUser = usersRepository.findUserByNickname(facebookLoginForm.getNickname());
        if(!foundUser.isPresent()) {
            User user = User.builder()
                    .createdAt(LocalDateTime.now())
                    .email(facebookLoginForm.getEmail())
                    .nickname(facebookLoginForm.getNickname())
                    .hashPassword(facebookLoginForm.getAccessToken())
                    .role(Role.USER)
                    .state(State.CONFIRMED)
                    .build();
            usersRepository.save(user);
            profileRepository.save(Profile.builder()
                    .bio("Your biography")
                    .user(user)
                    .build());
        }
        UserDetailsImpl userDetails = (UserDetailsImpl)userDetailsService.loadUserByUsername(facebookLoginForm.getNickname());
        String token = tokenService.generateToken(userDetails);
        UserDto userDto = UserDto.from(userDetails.getUser());
        return new UserRestDataDto(token, userDto);
    }
}
