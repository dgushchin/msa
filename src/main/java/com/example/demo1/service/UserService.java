package com.example.demo1.service;

import com.example.demo1.dto.User;
import com.example.demo1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User createUser(String name){
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }

    public User editUser(int id, String name){
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseGet(User::new);
        user.setName(name);
        return userRepository.save(user);
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
}
