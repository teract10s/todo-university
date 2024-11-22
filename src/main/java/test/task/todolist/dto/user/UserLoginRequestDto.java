package test.task.todolist.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
        @NotBlank @Email @Size(max = 255) String email;
        @NotBlank @Size(min = 8, max = 255) String password;
}
