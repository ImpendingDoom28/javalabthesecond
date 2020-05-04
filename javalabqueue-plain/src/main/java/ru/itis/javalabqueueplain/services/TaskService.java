package ru.itis.javalabqueueplain.services;

import ru.itis.javalabqueueplain.models.Task;

public interface TaskService {
    void save(Task task);
    Task getByTaskId(String taskId);
}
