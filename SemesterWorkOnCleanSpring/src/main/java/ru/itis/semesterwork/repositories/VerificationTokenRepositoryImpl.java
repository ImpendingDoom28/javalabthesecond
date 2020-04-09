package ru.itis.semesterwork.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.semesterwork.models.VerificationToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


//Uses entityManager
@Repository
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<VerificationToken> save(VerificationToken entity) {
        entityManager.persist(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<VerificationToken> delete(VerificationToken entity) {
        return Optional.empty();
    }

    @Override
    public Optional<VerificationToken> find(VerificationToken entity) {
        return Optional.empty();
    }

    @Override
    public Optional<VerificationToken> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<VerificationToken> update(VerificationToken entity) {
        return Optional.empty();
    }

    @Override
    public Optional<VerificationToken> findByValue(String token) {
        VerificationToken verificationToken =
                (VerificationToken) entityManager
                        .createNativeQuery("SELECT * FROM verification_token where verification_token.value = ?", VerificationToken.class)
                        .setParameter(1, token)
                        .getSingleResult();
        return Optional.ofNullable(verificationToken);
    }
}
