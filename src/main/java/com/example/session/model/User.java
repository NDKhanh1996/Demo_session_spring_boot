package com.example.session.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
    private String id;
    private String email;
    private String fullName;
    private String hashed_password;
    private State state;

}