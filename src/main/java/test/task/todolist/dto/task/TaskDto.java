package test.task.todolist.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.task.todolist.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    @JsonFormat(pattern = "dd:MM:yyyy' 'HH:mm")
    private LocalDateTime deadline;
    private Status status;
}
