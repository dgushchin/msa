package com.example.demo1.controllers;

import com.example.demo1.dto.User;
import com.example.demo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @PostMapping("")
    public User createUser(@RequestParam(value = "name") String name){
        return userService.createUser(name);
    }
    @PutMapping("/{id}")
    public User editUser(@PathVariable(value = "id") int id, @RequestParam(value="name") String name){
        return userService.editUser(id, name);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") int id){
        userService.deleteUser(id);
        return "user was deleted";
    }
}
