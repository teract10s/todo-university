package test.task.todolist.service;


import test.task.todolist.dto.user.UserRegistrationRequestDto;
import test.task.todolist.dto.user.UserResponseDto;
import test.task.todolist.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}
