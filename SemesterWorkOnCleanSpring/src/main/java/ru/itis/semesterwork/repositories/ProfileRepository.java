package ru.itis.semesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.semesterwork.models.Profile;
import ru.itis.semesterwork.models.User;

import java.util.Optional;

@Repository(value = "myProfileRepository")
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findProfileByUser(User user);
    @Query(nativeQuery = true, value = "SELECT * FROM user_profile WHERE user_id = ?1")
    Optional<Profile> findProfileByUserId(Long userId);
}
