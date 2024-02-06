package com.nprcz.dmcustomer.controller.api;

import jakarta.validation.constraints.*;

import java.util.Optional;

record TestRequest(
        @NotNull(message = "userEmailNotNullTestMessages") @NotBlank(message = "userEmailNotBlankTestMessages") @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "userEmailWrongPatternTestMessage")
        String userEmailTest,

        @NotNull(message = "userPasswordNotNullTestMessages") @NotBlank(message = "userPasswordNotNullMessage") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$", message = "userPasswordWrongPatternTestMessage")
        String userPassword,


        @NotNull(message = "PasswordNotNullTestMessages") @NotBlank(message = "PasswordNotBlankTestMessages")
        String confirmUserPassword)
{
    @AssertTrue(message = "PasswordNonEqualTestMessages") boolean isUserPasswordConfirmed() {
        return Optional.ofNullable(userPassword).

                map(userPassword -> userPassword.equals(confirmUserPassword)).

                orElse(false);
    }
}