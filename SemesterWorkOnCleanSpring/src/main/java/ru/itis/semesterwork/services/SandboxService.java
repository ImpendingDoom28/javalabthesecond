package ru.itis.semesterwork.services;

import ru.itis.semesterwork.dto.SandboxDto;
import ru.itis.semesterwork.models.Sandbox;
import ru.itis.semesterwork.models.User;

public interface SandboxService {

    void saveOrUpdate(SandboxDto sandboxDto, User user);

    SandboxDto load(String id);

    SandboxDto loadWithRandomId();

    void deleteById(String id);

}
