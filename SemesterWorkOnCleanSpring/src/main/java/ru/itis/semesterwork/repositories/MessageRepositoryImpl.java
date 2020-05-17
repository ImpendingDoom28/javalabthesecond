package ru.itis.semesterwork.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.semesterwork.models.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageRepositoryImpl implements MessageRepository{

    //language=SQL
    private final String SQL_FIND_ALL = "SELECT * FROM message";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Message> save(Message entity) {
        entityManager.persist(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<Message> delete(Message entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Message> find(Message entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Message> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Message> update(Message entity) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return entityManager.createNativeQuery(SQL_FIND_ALL, Message.class).getResultList();
    }
}
