package ru.itis.semesterwork.repositories;

import ru.itis.semesterwork.models.Profile;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Optional<Profile> findProfileByUserId(Long userId);
}
