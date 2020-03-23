package ru.itis.mail.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.mail.models.FileInfo;
import ru.itis.mail.repository.FilesRepository;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private Environment environment;

    @Override
    public void saveFile(MultipartFile multipartFile) {
        if(!multipartFile.isEmpty()) {
            try {
                FileInfo fileInfo = FileInfo.builder()
                        .storageFileName(UUID.randomUUID().toString())
                        .originalFileName(multipartFile.getOriginalFilename())
                        .type(multipartFile.getContentType())
                        .size(multipartFile.getSize())
                        .build();
                if(filesRepository.save(fileInfo).isPresent()) {
                    createStorage();
                    File file = new File(environment.getProperty("storage.path") +
                            File.separator +
                            fileInfo.getStorageFileName() +
                            Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf(".")));
                    multipartFile.transferTo(file);
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("File is empty!");
    }

    @Override
    public File getFile(String originalFileName) {
        Optional<FileInfo> optionalFileInfo = filesRepository.findByOriginalFileName(originalFileName);
        if(optionalFileInfo.isPresent()) {
            FileInfo info = optionalFileInfo.get();
            return new File(environment.getProperty("storage.path")
                    + File.separator + info.getStorageFileName() + info.getOriginalFileName().substring(info.getOriginalFileName().lastIndexOf(".")));
        } else
            throw new IllegalArgumentException("No such file found");
    }

    private void createStorage() {
        File storage = new File(environment.getProperty("storage.path"));
        if(!storage.exists()) storage.mkdir();
    }
}
