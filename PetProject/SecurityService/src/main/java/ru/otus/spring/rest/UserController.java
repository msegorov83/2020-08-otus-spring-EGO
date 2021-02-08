package ru.otus.spring.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.User;
import ru.otus.spring.repostory.RoleRepository;
import ru.otus.spring.repostory.UserRepository;

@RestController
public class UserController {

    private RoleRepository roleRepository;

    private UserRepository userRepository;

    @PostMapping("/user/create")
    public void createUser(@RequestBody User user){
        userRepository.save(user);
    }
}
