package com.example.session;

import com.example.session.model.State;
import com.example.session.model.User;
import com.example.session.repository.UserRepo;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TestUserRepo {
    @Test
    public void addUser() {
        UserRepo userRepo = new UserRepo();

        User user = userRepo.addUser("a", "a@gmail.com", "OX-12312am34234", State.PENDING);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getFullName()).isNotBlank();
        assertThat(user.getEmail()).isNotBlank();
        assertThat(user.getHashed_password()).isNotBlank();
        assertThat(user.getState()).isNotNull();
    }

    @Test
    public void addUserWithStatePending() {
        UserRepo userRepo = new UserRepo();

        User user = userRepo.addUser("a", "a@gmail.com", "OX-12312am34234");
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotBlank();
        assertThat(user.getFullName()).isNotBlank();
        assertThat(user.getEmail()).isNotBlank();
        assertThat(user.getHashed_password()).isNotBlank();
        assertThat(user.getState()).isEqualTo(State.PENDING);
    }

    @Test
    public void isEmailExist() {
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("a", "a@gmail.com", "OX-12312am34234");
        userRepo.addUser("John Levy", "levy@gmail.com", "OX-1231am123123");
        userRepo.addUser("Ana Duo", "Ana@gmail.com", "OX-657bnz");
        userRepo.addUser("Ken James", "ken@gmail.com", "OX-pm23xcs");

        assertThat(userRepo.isEmailExist("a@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("Ana@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("ana@gmail.com")).isTrue();
        assertThat(userRepo.isEmailExist("anb@gmail.com")).isFalse();
    }

    @Test
    public void findByEmail() {
        UserRepo userRepo = new UserRepo();
        userRepo.addUser("a", "a@gmail.com", "OX-12312am34234");
        userRepo.addUser("John Levy", "levy@gmail.com", "OX-1231am123123");
        userRepo.addUser("Ana Duo", "Ana@gmail.com", "OX-657bnz");
        userRepo.addUser("Ken James", "ken@gmail.com", "OX-pm23xcs");

        assertThat(userRepo.findByEmail("levy@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("a@gmail.com")).isPresent();
        assertThat(userRepo.findByEmail("a12312@gmail.com")).isNotPresent();
    }
}