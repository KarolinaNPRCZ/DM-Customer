package com.nprcz.dmcustomer;


import lombok.Builder;

@Builder(toBuilder = true)
public record LoginRequest(String userEmail,
                           String userPassword) {

}
