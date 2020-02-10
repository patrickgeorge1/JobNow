package com.app.Controllers;


import com.app.Models.User;
import com.app.Repository.UserRepository;
import com.app.Services.ExempluServiciu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@RestController
public class PatrickController {

    @Autowired
    ExempluServiciu serviciu1;
    @Autowired
    UserRepository userRepository;


    @RequestMapping("/")
    public String index() {
        return serviciu1.write("Daaaaa si  Nuuuuu");
    }

    @RequestMapping("/renderView")
    public ModelAndView fm(Model model) {
        String fm = "FreeMarker a fost injectat !";

            HashMap<String, Object> params = new HashMap<>();
            params.put("cheie_mapa", fm);


        return new ModelAndView("index", params);
    }

    @RequestMapping("/add")
    public String Add() {
        User n = new User();
        n.setName("alin");
        n.setEmail("sdasd@gmail.com");
        userRepository.save(n);
        return "Saved";
    }

    @RequestMapping(path="/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
