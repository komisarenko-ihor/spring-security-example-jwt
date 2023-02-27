package com.example.springsecurityexamplejwt.bootstrap;

import com.example.springsecurityexamplejwt.domain.Role;
import com.example.springsecurityexamplejwt.domain.User;
import com.example.springsecurityexamplejwt.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.saveRole(Role.builder().name("ROLE_ADMIN").build());
        userService.saveRole(Role.builder().name("ROLE_USER").build());
        userService.saveRole(Role.builder().name("ROLE_MANAGER").build());

        userService.saveUser(User.builder().name("Name1").username("user1").password("password1").build());
        userService.saveUser(User.builder().name("Name2").username("user2").password("password2").build());
        userService.saveUser(User.builder().name("Name3").username("user3").password("password3").build());
        userService.saveUser(User.builder().name("Name4").username("user4").password("password4").build());

        userService.addRoleToUser("user1", "ROLE_ADMIN");
        userService.addRoleToUser("user1", "ROLE_USER");
        userService.addRoleToUser("user2", "ROLE_ADMIN");
        userService.addRoleToUser("user3", "ROLE_USER");
        userService.addRoleToUser("user4", "ROLE_MANAGER");
    }
}
