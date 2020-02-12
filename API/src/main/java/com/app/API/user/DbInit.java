package com.app.API.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();



        User patrick = new User("patrick", passwordEncoder.encode("patrick"), "Patrick George",  "", 7, new Date(), "ROLE_ADMIN");
        User ion = new User("ion", passwordEncoder.encode("ion"), "Ion George", "", 7, new Date(), "ROLE_USER");
        User ticket = new User("ticket", passwordEncoder.encode("ticket"), "Ion George", "", 7, new Date(), "ACCESS_TICKET, ROLE_USER");

        List<User> users = Arrays.asList(patrick, ion, ticket);
        this.userRepository.saveAll(users);
    }
}
