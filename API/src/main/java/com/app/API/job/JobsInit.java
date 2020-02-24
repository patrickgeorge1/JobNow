package com.app.API.job;

import com.app.API.category.Category;
import com.app.API.category.CategoryRepository;
import com.app.API.user.User;
import com.app.API.user.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service("JobsInit")
@Order(value = 2)
public class JobsInit implements CommandLineRunner {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Faker faker;


    @Override
    public void run(String... args) throws Exception {
        this.jobRepository.deleteAll();
        List<User> users = userRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        Random random = new Random();

        Job new_job;
        for (int i = 0; i <= 100; i++) {
            new_job = new Job((long) i,
                    users.get(random.nextInt(users.size())),
                    users.get(random.nextInt(users.size())),
                    categories.get(random.nextInt(categories.size())),
                    10 + random.nextFloat(), faker.job().title(),
                    faker.job().keySkills(), 0, new Date(),
                    100 + Math.random() * (10000 - (100)),
                    100 + Math.random() * (10000 - (100)));
            this.jobRepository.save(new_job);
        }
    }
}
