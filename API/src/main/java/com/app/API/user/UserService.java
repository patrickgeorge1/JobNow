package com.app.API.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllAdmin() {
        return new ArrayList<>(userRepository.findAll());
    }

    public List<User> getAll() {
        List<User> users = this.getAllAdmin();
        for (User u : users) { u.setPassword("invisible"); u.setPermissions("invisible"); }
        return users;

    }

    public void addUser(String username, String password, String realName) {
        User new_user = new User(username, passwordEncoder.encode(password), realName, "no stats", 7, new Date(), "ROLE_USER");

    }
}
