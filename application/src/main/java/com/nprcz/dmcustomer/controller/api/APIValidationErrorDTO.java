package com.nprcz.dmcustomer.controller.api;

import java.util.List;

public record APIValidationErrorDTO(List<FieldErrorMessages> errors) {
}
