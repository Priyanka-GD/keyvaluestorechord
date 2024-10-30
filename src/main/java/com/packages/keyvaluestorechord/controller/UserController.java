package com.packages.keyvaluestorechord.controller;

import com.packages.keyvaluestorechord.model.User;
import com.packages.keyvaluestorechord.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/assign")
    public String assignUserToProcess (@RequestParam String name, @RequestParam String address, @RequestParam String email) {
        User user = new User(name, address, email);
        return userService.assignUserToProcess(user);
    }

    @GetMapping("/get/{userName}")
    public User getUserFromProcess (@PathVariable String userName) {
        return userService.getUserFromProcess(userName);
    }
}
