package ru.raskopova.testProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raskopova.testProject.model.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    Task findByOrderNumber( int orderNumber);
    List<Task> findAllByStatus(String status);
    List<Task> findAllByActionDateBetween(LocalDateTime date1, LocalDateTime date2);
}
