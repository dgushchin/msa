package com.example.demo1.repository;

import com.example.demo1.dto.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void findById() {
        String name = "Leo";
        userRepository.save(new User(name));

        User user = userRepository.findById(0).orElseGet(User::new);

        assertThat(user.getName(), Matchers.containsString(name));
    }
}