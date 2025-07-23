package com.SpringBoot.Ecomm.controller;


import com.SpringBoot.Ecomm.model.User;
import com.SpringBoot.Ecomm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500"})
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user)
    {
        return userService.loginUser(user.getEmail() , user.getPassword());
    }

    @GetMapping("/getUsers")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }




}
