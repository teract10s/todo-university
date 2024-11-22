package test.task.todolist.service.impl;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.todolist.dto.task.CreateTaskDto;
import test.task.todolist.dto.task.TaskDto;
import test.task.todolist.dto.task.UpdateTaskDto;
import test.task.todolist.exception.NotFoundException;
import test.task.todolist.exception.PermissionDeniedException;
import test.task.todolist.exception.WrongDeadlineException;
import test.task.todolist.exception.WrongStatusException;
import test.task.todolist.mapper.TaskMapper;
import test.task.todolist.model.Task;
import test.task.todolist.model.Status;
import test.task.todolist.model.User;
import test.task.todolist.repository.TaskRepository;
import test.task.todolist.repository.UserRepository;
import test.task.todolist.service.TaskService;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<TaskDto> findAllTasks(Long userId) {
        return taskRepository.findAllByUserId(userId).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public TaskDto getTaskById(Long id, Long userId) {
        Task task = taskRepository.getReferenceById(id);
        if (!Objects.equals(task.getUser().getId(), userId)) {
            throw new PermissionDeniedException("You don't have permission to the task with id: " + id);
        }
        return taskMapper.toDto(task);
    }

    @Override
    public List<TaskDto> findAllTasksByStatus(Long userId, String status) {
        try {
            Status taskStatus = Status.valueOf(status.toUpperCase());
            return taskRepository.findAllByStatusAndUserId(taskStatus, userId).stream()
                    .map(taskMapper::toDto)
                    .toList();
        } catch (IllegalArgumentException ex) {
            throw new WrongStatusException(
                    String.format("Non-existent status: %s was requested", status
                    ));
        }
    }

    @Override
    public TaskDto createTask(Long userId, CreateTaskDto taskDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Can't find user by id: " + userId));
        Task notSavedTask = taskMapper.toModel(taskDto);
        notSavedTask.setUser(user);
        if (taskDto.getDeadline() != null && LocalDateTime.now().isAfter(taskDto.getDeadline())) {
            throw new WrongDeadlineException("The deadline must be after the current time");
        }
        if (notSavedTask.getStatus() == null) {
            notSavedTask.setStatus(Status.NEW);
        }
        Task savedTask = taskRepository.save(notSavedTask);
        return taskMapper.toDto(savedTask);
    }

    @Transactional
    @Override
    public void deleteTask(Long userId, Long id) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Can't find user by id: " + userId));
        Task task = taskRepository.getReferenceById(id);
        if (!Objects.equals(task.getUser().getId(), user.getId())) {
            throw new PermissionDeniedException("Can't delete not your task");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDto updateTask(Long userId, Long id, UpdateTaskDto updateTaskDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Can't find user by id: " + userId));
        Task taskFromDb = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Can't find task by id: " + id));
        if (!Objects.equals(taskFromDb.getUser().getId(), user.getId())) {
            throw new PermissionDeniedException("Can't update not your task");
        }
        if (updateTaskDto.getDeadline() != null
                && LocalDateTime.now().isAfter(updateTaskDto.getDeadline())) {
            throw new WrongDeadlineException("The deadline must be after the current time");
        }
        taskMapper.updateBook(updateTaskDto, taskFromDb);
        return taskMapper.toDto(taskRepository.save(taskFromDb));
    }
}
