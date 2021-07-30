package ru.raskopova.testProject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raskopova.testProject.model.entity.Task;
import ru.raskopova.testProject.repository.TaskRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService
{
    private final TaskRepository taskRepository;
    private final String status = "Не выполнен"; //дефолтное значение статуса задания
    private final String comment= "Пустой комментарий"; // дефолтное значение комментария задания до обзвона


    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(taskRepository.findAll());
    }

    @Override
    public Task addTask(Task task) {
        LocalDateTime date = LocalDateTime.now();
        return taskRepository.save(new Task().setOrderNumber(task.getOrderNumber()).setStatus(status).setActionDate( date).setComment(comment));
    }

    @Override
    public Task getTaskByOrderID(int order_id) {
        return taskRepository.findByOrderNumber(order_id);
    }

    @Override
    public List<Task> getTaskByDatePeriod(LocalDate date1, LocalDate date2) {
        return taskRepository.findAllByActionDateBetween(date1.atTime(LocalTime.MIN), date2.atTime(LocalTime.MAX));
    }

    @Override
    public List<Task> getTaskByStatus(String status) {
        return taskRepository.findAllByStatus(status);
    }

    @Override
    public void addCallResult(int id, String status, String comment) {
        Task task1 = taskRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        task1.setStatus(status).setComment(comment);
        taskRepository.save(task1);
    }
}
