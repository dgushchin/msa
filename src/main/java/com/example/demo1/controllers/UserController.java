package com.example.demo1.controllers;

import com.example.demo1.dto.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/")
    public User createUser(@RequestParam(value = "name") String name){
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }
    @PutMapping("/{id}")
    public User editUser(@PathVariable(value = "id") int id, @RequestParam(value="name") String name){
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseGet(User::new);
        user.setName(name);
        return userRepository.save(user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable(value = "id") int id){
        userRepository.deleteById(id);
        return "user was deleted";
    }
}
