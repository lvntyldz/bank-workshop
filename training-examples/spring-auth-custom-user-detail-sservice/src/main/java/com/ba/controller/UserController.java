package com.ba.controller;

import com.ba.domain.Role;
import com.ba.domain.User;
import com.ba.repository.RoleRepository;
import com.ba.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/add")
    public String addUser() {

        Role role = roleRepository.findById(1L).get();//ROLE_USER

        User user = new User();
        user.setEmail("levent@aa.cc");
        user.setEnabled(true);
        user.setUsername("levent");
        user.setPassword(encoder.encode("123"));
        user.getRoles().add(role);

        userRepository.save(user);

        return "user created";
    }

    @GetMapping("/update")
    public String updateUser() {

        Role role = new Role();
        role.setName("ROLE_REPORTER");

        User user = userRepository.getUserByUsername("levent");
        user.setPassword(encoder.encode("1234"));
        user.getRoles().add(role);

        userRepository.save(user);

        return "user updated";
    }

    @GetMapping("/list")
    public List<User> listUsers() {

        List<User> users = new ArrayList<>();

        userRepository.findAll().iterator().forEachRemaining(users::add);

        return users;
    }

    @GetMapping("/list-roles")
    public List<Role> listRoles() {

        List<Role> roles = new ArrayList<>();

        roleRepository.findAll().iterator().forEachRemaining(roles::add);

        return roles;
    }

}
