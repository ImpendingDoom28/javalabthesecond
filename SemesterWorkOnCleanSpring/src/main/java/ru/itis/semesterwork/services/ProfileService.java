package ru.itis.semesterwork.services;

import ru.itis.semesterwork.dto.ProfileDto;

public interface ProfileService {

    ProfileDto getUserProfile(Long userId);
}
