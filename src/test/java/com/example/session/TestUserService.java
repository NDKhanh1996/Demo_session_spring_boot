package com.example.session;

import com.example.session.exeption.UserException;
import com.example.session.model.User;
import com.example.session.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TestUserService {
    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = userService.addUser("Davy John", "Davy@gmail.com", "abc1234");
        assertThat(user).isNotNull();
    }

    @Test
    public void login_when_account_is_pending() {
        userService.addUser("Davy John", "Davy@gmail.com", "abc1234");
        assertThatThrownBy(() -> { // check throw exception or not
            userService.login("Davy@gmail.com", "abc1234"); // input lambda function to check
        }).isInstanceOf(UserException.class) // check exception is UserException.class or not
                .hasMessageContaining("User is not activated"); // check alert is string "Password is incorrect" or not
    }

    @Test
    public void login_when_account_is_not_found() {
        assertThatThrownBy(() -> { // check throw exception or not
            userService.login("Dung@gmail.com", "abc1234"); // input lambda function to check
        }).isInstanceOf(UserException.class) // check exception is UserException.class or not
                .hasMessageContaining("User is not found"); // check alert is string "Password is incorrect" or not
    }

    @Test
    public void login_when_password_is_incorrect() {
        userService.addUserThenAutoActivate("Davy John", "Davy@gmail.com", "abc1234");
        assertThatThrownBy(() -> { // check throw exception or not
            userService.login("Davy@gmail.com", "abc12345"); // input lambda function to check
        }).isInstanceOf(UserException.class) // check exception is UserException.class or not
                .hasMessageContaining("Password is incorrect"); // check alert is string "Password is incorrect" or not
    }

    @Test
    public void login_successful() {
        userService.addUserThenAutoActivate("Davy John", "Davy@gmail.com", "abc1234");
        User user = userService.login("Davy@gmail.com", "abc1234");
        assertThat(user).isNotNull();
    }
}