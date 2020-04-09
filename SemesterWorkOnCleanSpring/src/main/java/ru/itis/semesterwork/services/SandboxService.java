package ru.itis.semesterwork.services;

import ru.itis.semesterwork.models.Sandbox;

public interface SandboxService {

    void save(Sandbox sandbox);

    void update(Sandbox sandbox);

    Sandbox load(String id);

    Sandbox loadWithRandomId();

    void deleteById(String id);

}
