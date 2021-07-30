package ru.raskopova.testProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.raskopova.testProject.model.entity.Task;
import ru.raskopova.testProject.service.TaskService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private static final String TASK_PATH = "/api/tasks";
    private final TaskService taskService;

    @GetMapping(value = TASK_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Task> getAllTasks( ){
        return taskService.getAllTasks();
    }

    @GetMapping(value = TASK_PATH+"/order/{orderNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  Task getAllTasksByOrderNum(@PathVariable int orderNumber){
        return taskService.getTaskByOrderID(orderNumber);
    }
    @GetMapping(value = TASK_PATH+"/status/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Task> getAllTasksByStatus(@PathVariable String status){
        return taskService.getTaskByStatus(status);
    }

    @GetMapping(value = TASK_PATH+"/getByPeriod", produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Task> getAllTasksByPeriod(@RequestParam("dateFrom")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom
            ,@RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo){
        return taskService.getTaskByDatePeriod(dateFrom,dateTo);
    }

    @PostMapping(value=TASK_PATH+"/newTask/{message}")
    public void addTask(@RequestBody Task task, @PathVariable String message ){
           if(message.equals("Не успеваю"))
            taskService.addTask(task);
    }

    @PostMapping(value = TASK_PATH+"/callResult")
    public void callResultMethod(@RequestBody Task task){
      taskService.addCallResult(task.getId(),task.getStatus(),task.getComment());
    }
}
