package com.ba.controller;

import com.ba.model.Authorities;
import com.ba.model.User;
import com.ba.repository.AuthorityRepository;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @GetMapping("/add/{username}/{password}")
    public String addUser(@PathVariable String username, @PathVariable String password) {

        User user = new User();
        user.setEnabled(true);
        user.setPassword("{noop}" + password);
        user.setUsername(username);

        userRepository.save(user);

        Authorities authorities = new Authorities();
        authorities.setAuthority("ROLE_USER");
        authorities.setUsername(username);

        authorityRepository.save(authorities);

        return "user created";
    }

}
