package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.dto.ProfileDto;
import ru.itis.semesterwork.repositories.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    @Qualifier("myProfileRepository")
    private ProfileRepository profileRepository;

    @Override
    public ProfileDto getUserProfile(Long userId) {
        return null;
    }
}
