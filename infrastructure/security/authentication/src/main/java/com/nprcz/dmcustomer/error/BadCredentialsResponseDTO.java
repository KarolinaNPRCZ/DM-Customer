package com.nprcz.dmcustomer.error;

import java.util.List;

 public record BadCredentialsResponseDTO(List<String> errors) {
}
