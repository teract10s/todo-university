package test.task.todolist.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import test.task.todolist.dto.task.CreateTaskDto;
import test.task.todolist.dto.task.TaskDto;
import test.task.todolist.dto.task.UpdateTaskDto;
import test.task.todolist.model.Task;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl")
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toModel(TaskDto taskDto);

    Task toModel(CreateTaskDto taskDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBook(UpdateTaskDto requestDto, @MappingTarget Task task);
}
