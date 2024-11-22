package test.task.todolist.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import test.task.todolist.validation.FieldsMatch;

@FieldsMatch(
        fields = {"password", "repeatedPassword"},
        message = "not repeated password"
)
@Getter
@Setter
public class UserRegistrationRequestDto {
        @NotBlank @Email @Size(max = 255) String email;
        @NotBlank @Size(min = 3, max = 255) String firstName;
        @NotBlank @Size(min = 3, max = 255) String lastName;
        @NotBlank @Size(min = 8, max = 255) String password;
        @NotBlank @Size(min = 8, max = 255) String repeatedPassword;
}
