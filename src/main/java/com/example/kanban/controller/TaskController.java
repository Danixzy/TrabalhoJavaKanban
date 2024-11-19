package com.example.kanban.controller;

import com.example.kanban.dto.TaskResponse;
import com.example.kanban.model.Status;
import com.example.kanban.model.Priority;
import com.example.kanban.model.Task;
import com.example.kanban.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // Criar uma nova tarefa
    @PostMapping
    public TaskResponse createTask(@RequestBody Task task) {
        return new TaskResponse(service.createTask(task));
    }

    // Obter todas as tarefas
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return service.getAllTasks().stream().map(TaskResponse::new).toList();
    }

    // Atualizar uma tarefa existente
    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody Task task) {
        return new TaskResponse(service.updateTask(id, task));
    }

    // Excluir uma tarefa
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
    }

    // Mover uma tarefa
    @PutMapping("/{id}/move")
    public TaskResponse moveTask(@PathVariable Long id) {
        return new TaskResponse(service.moveTask(id));
    }

    // Ordenar tarefas por prioridade dentro de uma coluna
    @GetMapping("/status/{status}/sorted")
    public List<TaskResponse> getTasksByStatusSorted(@PathVariable Status status) {
        return service.getTasksByStatusSortedByPriority(status).stream().map(TaskResponse::new).toList();
    }

    // Filtrar tarefas por prioridade e data limite
    @GetMapping("/filter")
    public List<TaskResponse> filterTasks(
            @RequestParam Priority priority,
            @RequestParam LocalDate dueDate) {
        return service.filterTasksByPriorityAndDueDate(priority, dueDate).stream().map(TaskResponse::new).toList();
    }

    // Gerar relatório de tarefas
    @GetMapping("/report")
    public Map<Status, List<TaskResponse>> generateReport() {
        return service.generateReport(LocalDate.now()).entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().map(TaskResponse::new).toList()
                ));
    }
}
