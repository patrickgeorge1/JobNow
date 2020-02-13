package com.app.API.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping(path = "api/admin/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsersAdmin() {
        return userService.getAllAdmin();
    }

    @RequestMapping(path = "api/public/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public @ResponseBody String addUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("real_name") String realName) {
        try {
            userService.addUser(username, password, realName);
            return "Success";
        } catch (Exception e) {
            return "Failed";
        }
    }
}
