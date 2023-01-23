package com.example.demo1.controllers;


import com.example.demo1.dto.Greeting;
import com.example.demo1.dto.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MainController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final UserRepository userRepository;

    @Autowired
    public MainController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/greeting")
    public Greeting getGreeting(@RequestParam(value = "name",defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(),String.format(template, name));
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @PostMapping("/create")
    public User createUser(@RequestParam(value = "name") String name){
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }
    @PutMapping("/edit/{id}")
    public User editUser(@PathVariable(value = "id") int id, @RequestParam(value="name") String name){
        User user = userRepository.findById(id).get();
        user.setName(name);
        return userRepository.save(user);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") int id){
        userRepository.deleteById(id);
        return "user was deleted";
    }

}
