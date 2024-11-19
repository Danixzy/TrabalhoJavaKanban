package com.example.kanban.service;

import com.example.kanban.model.Task;
import com.example.kanban.model.Status;
import com.example.kanban.model.Priority;
import com.example.kanban.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Criação de uma nova tarefa
    public Task createTask(Task task) {
        return repository.save(task);
    }

    // Obter todas as tarefas
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // Atualizar tarefa existente
    public Task updateTask(Long id, Task task) {
        Task existingTask = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        return repository.save(existingTask);
    }

    // Excluir uma tarefa
    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    // Mover tarefa (exemplo: alterar status)
    public Task moveTask(Long id) {
        Task task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(task.getStatus() == Status.TO_DO ? Status.IN_PROGRESS : Status.DONE);
        return repository.save(task);
    }

    // Ordenar tarefas por status e prioridade
    public List<Task> getTasksByStatusSortedByPriority(Status status) {
        return repository.findByStatusOrderByPriority(status);
    }

    // Filtrar tarefas por prioridade e data limite
    public List<Task> filterTasksByPriorityAndDueDate(Priority priority, LocalDate dueDate) {
        return repository.findByPriorityAndDueDate(priority, dueDate);
    }

    // Gerar relatório de tarefas, destacando atrasadas
    public Map<Status, List<Task>> generateReport(LocalDate currentDate) {
        List<Task> overdueTasks = repository.findByDueDateBeforeAndStatusNot(currentDate, Status.DONE);
        Map<Status, List<Task>> tasksGroupedByStatus = repository.findAll().stream()
                .collect(Collectors.groupingBy(Task::getStatus));

        // Adiciona destaque para tarefas atrasadas
        tasksGroupedByStatus.put(Status.TO_DO, overdueTasks);
        return tasksGroupedByStatus;
    }
}
