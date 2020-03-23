package ru.itis.mail.repository;

import ru.itis.mail.models.FileInfo;

import java.util.Optional;

public interface FilesRepository extends CrudRepository<Long, FileInfo>{

    Optional<FileInfo> findByOriginalFileName(String originalFileName);
}
