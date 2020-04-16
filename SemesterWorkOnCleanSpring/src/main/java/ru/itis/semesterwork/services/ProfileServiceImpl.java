package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.semesterwork.dto.ProfileDto;
import ru.itis.semesterwork.repositories.ProfileRepository;

@Component
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public ProfileDto getUserProfile(Long userId) {
        return null;
    }
}
