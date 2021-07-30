package ru.raskopova.testProject.service;

import ru.raskopova.testProject.model.entity.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {
    /**
     * @return возвращает лист всех заданий
     */
    List<Task> getAllTasks();

    /**
     * @return добавляет новое задание
     */
    Task addTask(Task task);

    /**
     * @param order_id
     * @return возвращает задание по номеру заказа
     */
    Task getTaskByOrderID(int order_id);

    /**
     * @param date1
     * @param date2
     * @return возвращает задания в диапазоне указанных дат
     */
    List<Task> getTaskByDatePeriod(LocalDate date1, LocalDate date2);

    /**
     * @param status
     * @return возвращает задания по статусу
     */
    List<Task> getTaskByStatus (String status);

    /**
     *
     */
    void addCallResult(int id, String status, String comment);

}
