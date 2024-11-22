package test.task.todolist.service;

import java.util.List;
import test.task.todolist.dto.task.CreateTaskDto;
import test.task.todolist.dto.task.TaskDto;
import test.task.todolist.dto.task.UpdateTaskDto;

public interface TaskService {

    List<TaskDto> findAllTasksByStatus(Long userId, String status);

    TaskDto createTask(Long userId, CreateTaskDto taskDto);

    void deleteTask(Long userId, Long id);

    TaskDto updateTask(Long userId, Long id, UpdateTaskDto updateTaskDto);

    List<TaskDto> findAllTasks(Long userId);

    TaskDto getTaskById(Long id, Long userId);
}

