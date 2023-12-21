package firstApi.com.example.DockerMongoExTcLombkWeb.infrastructure.adapters.userrestcontroller;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Getter
@AllArgsConstructor
//TODO make this class not public
public class UserRegisterRequest {
    @NotNull
    @NotBlank
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    String UserEmail;
    @NotNull
    @NotBlank
    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$",
            message = "Password must contains at least one digit, one letter and can't contains whitespace characters")
    String userPassword;

    @NotBlank(message = "Confirm Password is reqired")
    private String confirmUserPassword;

    @AssertTrue(message = "Passwords are not the same")
    public boolean isUserPasswordConfirmed() {
        return userPassword.equals(confirmUserPassword);
    }


}




