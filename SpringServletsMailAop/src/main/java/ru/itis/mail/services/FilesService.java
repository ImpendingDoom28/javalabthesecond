package ru.itis.mail.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FilesService {

    void saveFile(MultipartFile multipartFile);

    File getFile(String originalFileName);
}
