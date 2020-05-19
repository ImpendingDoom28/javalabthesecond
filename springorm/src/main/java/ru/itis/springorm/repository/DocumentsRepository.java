package ru.itis.springorm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springorm.models.Document;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Long> {
}

