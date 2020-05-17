package ru.itis.semesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.semesterwork.dto.SandboxDto;
import ru.itis.semesterwork.models.Sandbox;
import ru.itis.semesterwork.models.User;
import ru.itis.semesterwork.repositories.SandboxRepository;

import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.UUID;

@Service
public class SandboxServiceImpl implements SandboxService {

    @Autowired
    private SandboxRepository sandboxRepository;
    @Value("${app.sandbox.id}")
    private String templateId;

    @Override
    public void saveOrUpdate(SandboxDto sandboxDto, User user) {
        Sandbox sandboxToSave = Sandbox.builder()
                .cssCode(sandboxDto.getCssCode())
                .htmlCode(sandboxDto.getHtmlCode())
                .jsCode(sandboxDto.getJsCode())
                .id(sandboxDto.getId())
                .name(sandboxDto.getName())
                .user(user)
                .build();
        try {
            Optional<Sandbox> foundSandbox = sandboxRepository.findById(sandboxDto.getId());
            if(foundSandbox.isPresent()) sandboxRepository.update(sandboxToSave);
        } catch(NoResultException e) {
            sandboxRepository.save(sandboxToSave);
        }
    }

    @Override
    public SandboxDto load(String id) {
        return SandboxDto.from(sandboxRepository.findById(id).get());
    }

    @Override
    public SandboxDto loadWithRandomId() {
        Optional<Sandbox> templateSandbox = sandboxRepository.findById(templateId);
        if(templateSandbox.isPresent()) {
            Sandbox sandbox = templateSandbox.get();
            String uuid = UUID.randomUUID().toString();
            sandbox.setId(uuid);
            return SandboxDto.from(sandbox);
        } else throw new IllegalArgumentException("No template sandbox found!!! OMG!!!");
    }

    @Override
    public void deleteById(String id) {

    }
}
