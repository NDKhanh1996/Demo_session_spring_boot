package com.example.session;

import com.example.session.hash.Hashing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

@SpringBootTest
public class TestHash {
    @Autowired
    private Hashing hash;

    @Test
    public void hashPassword() {
        var passwords = List.of("abc", "qwerty", "ox-123", "_&?LLk2312");
        for (String password : passwords) {
            String hashed_pass = hash.hasPassword(password);
            assertThat(hashed_pass).isNotNull();
        }
    }

    @Test
    public void validatePassword() {
        var passwords = List.of("abc1232-+", "qwerty19103", "ox-123", "_&?LLk2312");
        for (String password : passwords) {
            String hashed_pass = hash.hasPassword(password);
            assertThat(hash.validatePassword(password, hashed_pass)).isTrue();
        }
        assertThat(hash.validatePassword("abc", "1000:2b0a88233d4985e0332ac16195f03782:9db7cd5f54a979f79ef2b61b0e23cc54")).isFalse();
    }
}