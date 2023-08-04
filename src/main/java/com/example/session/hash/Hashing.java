package com.example.session.hash;

public interface Hashing {
    public String hasPassword(String password);

    public boolean validatePassword(String originalPassword, String storedPassword);
}