package com.app.API.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path = "/api/public/admin/categories", method = RequestMethod.GET)
    public @ResponseBody
    List<Category> getAllCategoriesAdmin() {
        return categoryService.getAllAdmin();
    }

    @RequestMapping(path = "/api/public/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getAllCategories() { return categoryService.getAll(); }
}
