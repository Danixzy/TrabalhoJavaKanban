package com.example.kanban.dto;

import com.example.kanban.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate; // Ajustado para LocalDate
    private String priority;
    private String status;
    private LocalDateTime createdAt;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.dueDate = task.getDueDate(); // Mantém LocalDate
        this.priority = task.getPriority().toString();
        this.status = task.getStatus().toString();
        this.createdAt = task.getCreatedAt();
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
