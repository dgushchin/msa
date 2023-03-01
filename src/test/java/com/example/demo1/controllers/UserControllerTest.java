package com.example.demo1.controllers;

import com.example.demo1.dto.User;
import org.hamcrest.Matchers;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    private final UserController userController;

    private final MockMvc mockMvc;

    @Autowired
    public UserControllerTest(UserController userController, MockMvc mockMvc) {
        this.userController = userController;
        this.mockMvc = mockMvc;
    }

    @Test
    void getUsers() throws Exception{
        mockMvc.perform(get("/users/all"))
                .andDo(print())
                .andExpect(content().string("[]"))
                .andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception {
        String name = "Jhon";

        mockMvc.perform(post("/users/?name=" + name))
                .andDo(print())
                .andExpect(content().string(containsString("\"name\":\"" + name + "\"")))
                .andExpect(status().isOk());

    }

    @Test
    void editUser() throws Exception {
        int id = 0;
        String name = "Jhon";
        String newName = "Alex";

        mockMvc.perform(post("/users/?name={name}", name))
                .andExpect(status().isOk());

        mockMvc.perform(put("/users/{id}?name={newName}",id, newName))
                .andDo(print())
                .andExpect(content().string(containsString("\"name\":\"" + newName + "\"")))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
        int id = 0;
        String name = "Jhon";

        mockMvc.perform(post("/users/?name={name}", name))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/users/{id}",id))
                .andDo(print())
                .andExpect(content().string("user was deleted"))
                .andExpect(status().isOk());
    }

}