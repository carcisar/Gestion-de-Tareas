package com.cuaderno.task.service;


import com.cuaderno.task.entity.Priority;
import com.cuaderno.task.entity.Task;
import com.cuaderno.task.entity.TaskStatus;
import com.cuaderno.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CRUD Básico
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setDueDate(taskDetails.getDueDate());
        task.setPriority(taskDetails.getPriority());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByPriority(Priority priority) {
        return taskRepository.findByPriority(priority);
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }


    public List<Task> getTasksDueBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return taskRepository.findTasksDueBetween(startDate, endDate);
    }

    public List<Task> getOverdueTasks() {
        LocalDateTime currentDate = LocalDateTime.now();
        return taskRepository.findOverdueTasks(currentDate);
    }

    public Long countTasksByStatus(TaskStatus status) {
        return taskRepository.countByStatus(status);
    }

    public List<Task> searchTasksByKeyword(String keyword) {
        return taskRepository.searchByKeyword(keyword);
    }
}
