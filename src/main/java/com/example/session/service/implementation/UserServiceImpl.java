package com.example.session.service.implementation;

import com.example.session.exeption.UserException;
import com.example.session.hash.Hashing;
import com.example.session.model.State;
import com.example.session.model.User;
import com.example.session.repository.UserRepo;
import com.example.session.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private Hashing hashing;

    @Override
    public User login(String email, String password) {
        Optional<User> o_user = userRepo.findByEmail(email);
        if (o_user.isEmpty()) {
            throw new UserException("User is not found");
        }

        User user = o_user.get();
        // User want to log in need activated state
        System.out.println(o_user);
        System.out.println(user);

        if (user.getState() != State.ACTIVE) {
            throw new UserException("User is not activated");
        }
        if (hashing.validatePassword(password, o_user.get().getHashed_password())) {
            return user;
        } else {
            throw new UserException("Password is incorrect");
        }
    }

    @Override
    public boolean logout(String email) {
        return false;
    }

    @Override
    public User addUser(String fullName, String email, String password) {
        return userRepo.addUser(fullName, email, hashing.hasPassword(password));
    }

    @Override
    public User addUserThenAutoActivate(String fullName, String email, String password) {
        return userRepo.addUser(fullName, email, hashing.hasPassword(password), State.ACTIVE);
    }

    @Override
    public Boolean activateUser(String activation_code) {
        return null;
    }

    @Override
    public Boolean updatePassword(String email, String password) {
        return null;
    }

    @Override
    public Boolean updateEmail(String email, String newEmail) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}