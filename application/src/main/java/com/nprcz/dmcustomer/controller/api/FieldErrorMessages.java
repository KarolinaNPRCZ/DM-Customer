package com.nprcz.dmcustomer.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public record FieldErrorMessages(
            String field,
            List<String> messages
    ) {

        private final static ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String toString() {
            try {
                return objectMapper.writeValueAsString(this);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
}
