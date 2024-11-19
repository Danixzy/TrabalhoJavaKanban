package com.example.kanban.repository;

import com.example.kanban.model.Task;
import com.example.kanban.model.Status;
import com.example.kanban.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatusOrderByPriority(Status status);
    List<Task> findByPriorityAndDueDate(Priority priority, LocalDate dueDate);
    List<Task> findByDueDateBeforeAndStatusNot(LocalDate currentDate, Status status);
}
