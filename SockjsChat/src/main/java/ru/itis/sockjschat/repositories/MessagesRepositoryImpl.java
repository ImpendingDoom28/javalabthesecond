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
        try {
            entityManager.createNativeQuery("INSERT INTO message (id, message, room_id, user_id) VALUE (?,?,?,?)")
                    .setParameter(1, message.getId())
                    .setParameter(2,message.getMessage())
                    .setParameter(3, message.getRoomId())
                    .setParameter(4, message.getUser().getId()).executeUpdate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return Optional.of(message);
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
