package com.nprcz.dmcustomer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;


@Log4j2
@Getter
@AllArgsConstructor
@NoArgsConstructor
 class UserRegisterRequest {
    @NotNull(message = "{not.null}")
    @NotBlank(message = "{not.blank}")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "{wrong.email}")
    String userEmail;

    @NotNull(message = "{not.null}")
    @NotBlank(message = "{not.blank}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$",
            message = "{wrong.password}")
    String userPassword;

    @NotNull(message = "{not.null}")
    @NotBlank(message = "{not.blank}")
    String confirmUserPassword;

    @JsonIgnore
    @AssertTrue(message = "{not.equal}")
    public boolean isUserPasswordConfirmed() {
        return Optional.ofNullable(userPassword).map(userPassword -> userPassword.equals(confirmUserPassword)).orElse(false);
    }


}




