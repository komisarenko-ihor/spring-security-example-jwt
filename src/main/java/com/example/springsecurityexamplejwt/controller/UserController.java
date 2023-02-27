package com.example.springsecurityexamplejwt.controller;

import com.example.springsecurityexamplejwt.domain.Role;
import com.example.springsecurityexamplejwt.domain.User;
import com.example.springsecurityexamplejwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/users/all")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Role saveRole(@RequestBody Role role) {
        return userService.saveRole(role);
    }

    @PutMapping("/users")
    public void addRoleToUser(@RequestParam String username, @RequestParam String roleName) {
        userService.addRoleToUser(username, roleName);
    }

    @GetMapping("/users")
    public User getUser(@RequestParam String username) {
        return userService.getUser(username);
    }

}
