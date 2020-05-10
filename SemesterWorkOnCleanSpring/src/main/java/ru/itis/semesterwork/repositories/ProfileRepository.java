package ru.itis.semesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.semesterwork.models.Profile;
import ru.itis.semesterwork.models.User;

import java.util.Optional;

@Repository(value = "myProfileRepository")
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findProfileByUser(User user);
}
