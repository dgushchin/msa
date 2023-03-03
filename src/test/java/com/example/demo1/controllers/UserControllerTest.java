package com.example.demo1.controllers;

import com.example.demo1.dto.User;
import com.example.demo1.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {
    private final MockMvc mockMvc;

    @MockBean
    private final UserRepository userRepository;

    @Autowired
    public UserControllerTest(MockMvc mockMvc, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }

    @Test
    void getUsers() throws Exception{
        List<User> userList = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        mockMvc.perform(get("/users/all"))
                .andDo(print())
                .andExpect(jsonPath("$",hasSize(0)))
                .andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception {
        String name = "Jhon";
        User user = new User(name);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        mockMvc.perform(post("/users/?name=" + name))
                .andDo(print())
                .andExpect(content().string(containsString("\"name\":\"" + name + "\"")))
                .andExpect(status().isOk());

    }

    @Test
    void editUser() throws Exception {
        String name = "Jhon";
        String newName = "Alex";

        User user = new User(name);
        User newUser = new User(newName);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);
        Mockito.when(userRepository.findByName(Mockito.any())).thenReturn(user);

        mockMvc.perform(put("/users/{id}?name={newName}",user.getId(), newName))
                .andDo(print())
                .andExpect(content().string(containsString("\"name\":\"" + newName + "\"")))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {

        mockMvc.perform(delete("/users/{id}",0))
                .andDo(print())
                .andExpect(content().string("user was deleted"))
                .andExpect(status().isOk());
    }

}