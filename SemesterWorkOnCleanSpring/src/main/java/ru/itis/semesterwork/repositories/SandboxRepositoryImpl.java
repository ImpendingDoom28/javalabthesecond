package ru.itis.semesterwork.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.semesterwork.models.Sandbox;

import javax.persistence.EntityManager;
import java.util.Optional;

//Uses spring data jpa without spring boot
@Repository
public class SandboxRepositoryImpl implements SandboxRepository {

    //language=SQL
    private final String SQL_FIND_BY_ID = "SELECT * FROM sandbox WHERE sandbox.id = ?1";

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
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
    public Optional<Sandbox> findById(String id) {
        Sandbox sandbox = (Sandbox) entityManager
                .createNativeQuery(SQL_FIND_BY_ID, Sandbox.class)
                .setParameter(1, id)
                .getSingleResult();
        return Optional.ofNullable(sandbox);
    }

    @Override
    public Optional<Sandbox> update(Sandbox entity) {
        return Optional.empty();
    }
}
