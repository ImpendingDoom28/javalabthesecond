package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.dto.ProfileDto;
import ru.itis.semesterwork.models.Profile;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.repositories.ProfileRepository;
import ru.itis.semesterwork.repositories.UsersRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    @Qualifier("myProfileRepository")
    private ProfileRepository profileRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public ProfileDto getUserProfile(Long userId) {
        User user = usersRepository.findById(userId).get();
        ProfileDto profileDto = ProfileDto.from(profileRepository.findProfileByUser(user).get());
        System.out.println(profileDto);
        return profileDto;
    }
}
