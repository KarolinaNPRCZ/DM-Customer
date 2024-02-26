package com.nprcz.dmcustomer.controller.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
class ControllerTest {

    @PostMapping("/test-exception")
    ResponseEntity<String> testEndpointToTestValidationHandler(@RequestBody @Valid TestRequest requestBody) {
        return ResponseEntity.ok("the exception was not caught");
    }

    @GetMapping("/test-exception/{testParam}")
    ResponseEntity<String> testEndpointToTestMethodTypeException(@PathVariable Integer testParam) {
        return ResponseEntity.ok("the exception was not caught");
    }
}