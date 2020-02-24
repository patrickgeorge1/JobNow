package com.app.API.category;

import com.app.API.commons.Constants;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;


@Service
@Order(value = 0)
public class CategoryInit implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private Faker faker;

    @Override
    public void run(String... args) throws Exception {
        categoryRepository.deleteAll();

        Category category;
        for (int i = 0; i < 10; i++) {
            category = new Category();
            category.setId((long) i);
            category.setName(Constants.categoryNames.get(i));
            this.categoryRepository.save(category);
        }
    }
}
