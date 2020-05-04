package ru.itis.javalabqueueplain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalabqueueplain.models.Task;
import ru.itis.javalabqueueplain.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task getByTaskId(String taskId) {
        return taskRepository.findByTaskId(taskId).get();
    }
}
