package ru.itis.semesterwork.repositories;

import org.springframework.data.jpa.repository.Query;
import ru.itis.semesterwork.models.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long>{

    Optional<VerificationToken> findByValue(String token);
}
