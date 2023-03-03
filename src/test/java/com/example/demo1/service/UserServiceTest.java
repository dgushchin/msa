package com.example.demo1.service;

import com.example.demo1.dto.User;
import com.example.demo1.repository.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    private final UserService userService;

    @MockBean
    private final UserRepository userRepository;

    @Autowired
    UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Test
    void getAllUser() {
        Mockito.when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<User> userList = userService.getAllUser();

        assertNotNull(userList);
        assertThat(userList, Matchers.hasSize(0));
    }

    @Test
    void createUser() {
        String name = "Jhon";

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User(name));

        User user = userService.createUser(name);

        assertNotNull(user);
        assertThat(user.getName(), Matchers.containsString(name));
    }

    @Test
    void editUser() {
        String name = "Jhon";
        String newName = "Alex";

        Mockito.when(userRepository.findByName(Mockito.any())).thenReturn(new User(name));
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(new User(newName));

        User editUser = userService.editUser(1,newName);

        assertNotNull(editUser);
        assertThat(editUser.getName(), Matchers.containsString(newName));
    }
}