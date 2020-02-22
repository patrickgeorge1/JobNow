package com.app.API.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(path = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ObjectNode addUser(@RequestBody ObjectNode json) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        try {
            userService.addUser(json.get("username").asText(), json.get("password").asText(), json.get("real_name").asText());
            objectNode.put("status", "success");
        } catch (Exception e) {
            objectNode.put("status", "failed");
        }
        return objectNode;
    }

    @RequestMapping(path = "/checkToken", method = RequestMethod.POST)
    public @ResponseBody ObjectNode checkToken() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("token", "success");
        return  objectNode;
    }

    @RequestMapping(path = "api/public/admin/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsersAdmin() {
        return userService.getAllAdmin();
    }

    @RequestMapping(path = "api/public/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(path = "api/public/admin/give", method = RequestMethod.POST)
    public  @ResponseBody String givePermission(@RequestBody ObjectNode json) {
        try {
            userService.givePermission(json.get("to").asLong(), json.get("permission").asText());
            return "Success";

        } catch (Exception e) {
            return "Failed";
        }
    }

}
