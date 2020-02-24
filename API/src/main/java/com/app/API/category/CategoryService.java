package com.app.API.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>(categoryRepository.findAll());
        for (Category category : categories) {
            category.setRequirements(null);
        }
        return categories;
    }

    public List<Category> getAllAdmin() {
        return new ArrayList<>(categoryRepository.findAll());
    }
}
