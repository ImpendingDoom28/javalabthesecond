package ru.itis.semesterwork.repositories;

import ru.itis.semesterwork.models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long>{

    List findAll();
}
