package ru.itis.sockjschat.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.sockjschat.models.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Optional<User> save(User user) {
        entityManager.persist(user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> find(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<User> delete(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<User> findByLogin(String login) {
        try {
            User user = (User) entityManager.createNativeQuery("SELECT * FROM user WHERE user.login = ?", User.class)
                    .setParameter(1, login)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }
}
