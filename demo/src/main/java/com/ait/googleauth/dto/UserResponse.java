package com.ait.googleauth.dto;

import com.ait.googleauth.model.User;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    Long id;
    String name;
    String email;
    String role;

    public UserResponse(User user) {
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        role = user.getRole().name();
    }
}
