package ru.itis.semesterwork.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.semesterwork.models.Sandbox;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class SandboxRepositoryImpl implements SandboxRepository {

    //Запросы к бд
    //language=SQL
    private final String SQL_FIND_BY_ID = "SELECT * FROM sandbox WHERE sandbox.id = ?1";
    //language=SQL
    private final String SQL_UPDATE = "UPDATE sandbox SET " +
            "htmlCode = ?, cssCode = ?, jsCode = ? WHERE user_id = ?";

    //Контекст, в котором хранятся объекты, которые взаимодействовали с базой
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    //Эта аннотация означает, что метод работает в рамках одной транзакции
    @Transactional
    public Optional<Sandbox> save(Sandbox entity) {
        entityManager.persist(entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<Sandbox> delete(Sandbox entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Sandbox> find(Sandbox entity) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Sandbox> findById(String id) {
        Sandbox sandbox = (Sandbox) entityManager
                .createNativeQuery(SQL_FIND_BY_ID, Sandbox.class)
                .setParameter(1, id)
                .getSingleResult();
        return Optional.ofNullable(sandbox);
    }

    @Override
    @Transactional
    public Optional<Sandbox> update(Sandbox entity) {
        entityManager.createNativeQuery(SQL_UPDATE)
                .setParameter(1, entity.getHtmlCode())
                .setParameter(2, entity.getCssCode())
                .setParameter(3, entity.getJsCode())
                .setParameter(4, entity.getUser().getId())
                .executeUpdate();
        return Optional.of(entity);
    }
}
