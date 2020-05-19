package ru.itis.springorm.services;

import ru.itis.springorm.dto.InformationDto;

public interface FilesService {

    void init();

    void convert();

    void convertPngByUser(Long userId);

    InformationDto getInformation(Long userId);
}
