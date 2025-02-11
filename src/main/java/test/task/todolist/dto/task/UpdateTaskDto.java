package test.task.todolist.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import test.task.todolist.model.Status;

@Builder
@Data
public class UpdateTaskDto {
        private String name;
        private String description;
        private Status status;
        @JsonFormat(pattern = "dd:MM:yyyy HH:mm:ss")
        private LocalDateTime deadline;
}
