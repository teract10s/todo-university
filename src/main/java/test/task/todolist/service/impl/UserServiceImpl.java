package test.task.todolist.service.impl;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import test.task.todolist.dto.user.UserRegistrationRequestDto;
import test.task.todolist.dto.user.UserResponseDto;
import test.task.todolist.exception.RegistrationException;
import test.task.todolist.mapper.UserMapper;
import test.task.todolist.model.Role;
import test.task.todolist.model.User;
import test.task.todolist.repository.RoleRepository;
import test.task.todolist.repository.UserRepository;
import test.task.todolist.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RegistrationException("Unable to complete registration");
        }
        Role defaultRole = roleRepository.getRoleByName(Role.RoleName.USER);
        User user = userMapper.toUser(request);
        user.setRoles(Set.of(defaultRole));
        user.setPassword(request.getPassword());
        return userMapper.toDto(userRepository.save(user));
    }
}
