package com.example.session.repository;

import com.example.session.model.State;
import com.example.session.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>(); // need sOut to see what it is

    public User addUser(String fullName, String email, String hashed_pass, State state) {
        String id = UUID.randomUUID().toString();
        User user = User.builder()
                .id(id)
                .fullName(fullName)
                .email(email)
                .hashed_password(hashed_pass)
                .state(state)
                .build();
        users.put(id, user);
        return user;
    }

    public User addUser(String fullName, String email, String hashed_pass) {
        return addUser(fullName, email, hashed_pass, State.PENDING);
    }

    public boolean isEmailExist(String email) {
        return users.values().stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email)).findFirst();
    }
}