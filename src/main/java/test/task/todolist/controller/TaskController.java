package test.task.todolist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.task.todolist.dto.task.CreateTaskDto;
import test.task.todolist.dto.task.TaskDto;
import test.task.todolist.dto.task.UpdateTaskDto;
import test.task.todolist.model.Task;
import test.task.todolist.service.TaskService;
import test.task.todolist.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    @GetMapping("/{userId}")
    public String getUserTasks(@PathVariable Long userId, Model model) {
        model.addAttribute("tasks", taskService.findAllTasks(userId));
        model.addAttribute("userId", userId);
        return "task-list";
    }

    @GetMapping("/{userId}/{taskId}")
    public String getTaskDetails(@PathVariable Long userId, @PathVariable Long taskId, Model model) {
        TaskDto task = taskService.getTaskById(taskId, userId);
        model.addAttribute("task", task);
        model.addAttribute("userId", userId);
        return "task-details";
    }

    @GetMapping("/{userId}/new")
    public String createTaskForm(@PathVariable Long userId, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("userId", userId);
        return "create-task";
    }

    @PostMapping("/{userId}")
    public String createTask(@PathVariable Long userId, @ModelAttribute CreateTaskDto task) {
        taskService.createTask(userId, task);
        return "redirect:/tasks/" + userId;
    }

    @GetMapping("/{userId}/{taskId}/edit")
    public String editTaskForm(@PathVariable Long userId, @PathVariable Long taskId, Model model) {
        TaskDto task = taskService.getTaskById(taskId, userId);
        model.addAttribute("task", task);
        model.addAttribute("userId", userId);
        return "edit-task";
    }

    @PostMapping("/{userId}/{taskId}/update")
    public String updateTask(@PathVariable Long userId, @PathVariable Long taskId, @ModelAttribute Task updatedTask) {
        UpdateTaskDto task = UpdateTaskDto.builder().build();
        task.setName(updatedTask.getName());
        task.setDescription(updatedTask.getDescription());
        task.setDeadline(updatedTask.getDeadline());
        task.setStatus(updatedTask.getStatus());
        taskService.updateTask(userId, taskId, task);
        return "redirect:/tasks/" + userId;
    }

    @PostMapping("/{userId}/{taskId}/delete")
    public String deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        taskService.deleteTask(userId, taskId);
        return "redirect:/tasks/" + userId;
    }
}
