package ru.itis.sockjschat.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.sockjschat.models.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class MessagesRepositoryImpl implements MessagesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Message> save(Message message) {
        entityManager.persist(message);
        return Optional.empty();
    }

    @Override
    public Optional<Message> find(Message message) {
        return Optional.empty();
    }

    @Override
    public Optional<Message> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Message> delete(Message message) {
        return Optional.empty();
    }

    @Override
    public Optional<Message> update(Message message) {
        return Optional.empty();
    }
}
