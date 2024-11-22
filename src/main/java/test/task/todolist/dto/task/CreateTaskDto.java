package test.task.todolist.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import test.task.todolist.model.Status;

@Builder
@Data
public class CreateTaskDto {
        @NotBlank
        private String name;
        private String description;
        private Status status;
        @JsonFormat(pattern = "dd:MM:yyyy HH:mm:ss")
        private LocalDateTime deadline;
}
