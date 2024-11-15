package com.cuaderno.task.controller;


import com.cuaderno.task.entity.Priority;
import com.cuaderno.task.entity.Task;
import com.cuaderno.task.entity.TaskStatus;
import com.cuaderno.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // CRUD básico
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = taskService.saveTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        try {
            Task task = taskService.updateTask(id, updatedTask);
            return new ResponseEntity<>(task, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        List<Task> tasks = taskService.getTasksByUserId(userId);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    // Métodos personalizados
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable Priority priority) {
        List<Task> tasks = taskService.getTasksByPriority(priority);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/due-between")
    public ResponseEntity<List<Task>> getTasksDueBetween(
            @RequestParam("startDate") LocalDateTime startDate,
            @RequestParam("endDate") LocalDateTime endDate) {
        List<Task> tasks = taskService.getTasksDueBetween(startDate, endDate);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        List<Task> tasks = taskService.getOverdueTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countTasksByStatus(@PathVariable TaskStatus status) {
        Long count = taskService.countTasksByStatus(status);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasksByKeyword(@RequestParam("keyword") String keyword) {
        List<Task> tasks = taskService.searchTasksByKeyword(keyword);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}

