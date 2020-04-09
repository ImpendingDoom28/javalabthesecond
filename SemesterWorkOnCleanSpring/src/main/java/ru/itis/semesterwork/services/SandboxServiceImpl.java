package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.models.Sandbox;
import ru.itis.semesterwork.repositories.SandboxRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class SandboxServiceImpl implements SandboxService {

    @Autowired
    private SandboxRepository sandboxRepository;
    @Value("${app.sandbox.id}")
    private String templateId;

    @Override
    public void save(Sandbox sandbox) {
    }

    @Override
    public void update(Sandbox sandbox) {

    }

    @Override
    public Sandbox load(String id) {
        return null;
    }

    @Override
    public Sandbox loadWithRandomId() {
        Optional<Sandbox> templateSandbox = sandboxRepository.findById(templateId);
        if(templateSandbox.isPresent()) {
            Sandbox sandbox = templateSandbox.get();
            String uuid = UUID.randomUUID().toString();
            sandbox.setId(uuid);
            return sandbox;
        } else throw new IllegalArgumentException("No template sandbox found!!! OMG!!!");
    }

    @Override
    public void deleteById(String id) {

    }
}
