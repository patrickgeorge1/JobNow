package com.app.API.test;

import com.app.API.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/tests", method = RequestMethod.GET)
    public @ResponseBody List<Test> getAllTest() {
        return testService.getAllTests();
    }

    @RequestMapping(path = "/tests/{id}", method = RequestMethod.GET)
    public @ResponseBody Test getTest(@PathVariable Long id) {
        return testService.getTest(id);
    }

    @RequestMapping(path = "/tests", method = {RequestMethod.POST, RequestMethod.PUT})
    public void addTest(@RequestBody Test test) {
        testService.addTest(test);
    }

    @RequestMapping(path = "/tests/{id}", method = RequestMethod.DELETE)
    public void deleteTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }

    @RequestMapping(path = "/tests/{name}", method = RequestMethod.POST)
    public void addTest(@PathVariable String name) {
        Test test = new Test(name);
        testService.addTest(test);
    }


    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public @ResponseBody List<String> test() {
        List<String> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            users.add(user.toString());
        });
        return users;
    }

    @RequestMapping(path = "/show", method = RequestMethod.POST)
    public @ResponseBody List<String> testPost() {
        List<String> users = new ArrayList<>();
        users.add("POST METHOD");
        userRepository.findAll().forEach(user -> {
            users.add(user.toString());
        });
        return users;
    }

    @RequestMapping(path = "/show", method = RequestMethod.GET)
    public @ResponseBody List<String> testPostGET() {
        List<String> users = new ArrayList<>();
        users.add("GET METHOD");
        userRepository.findAll().forEach(user -> {
            users.add(user.toString());
        });
        return users;
    }

    @RequestMapping(path = "/api/public", method = RequestMethod.GET)
    public String apiPublic() {
        return "welcome to api public";
    }

    @RequestMapping(path = "/api/admin", method = RequestMethod.GET)
    public String apiAdmin() {
        return "welcome to api admin";
    }

    @RequestMapping(path = "/api/public/ticket/view", method = RequestMethod.POST)
    public String apiTicket() {
        return "ai vazut ticket";
    }

    // Should not exist
    // Purpose: To reset test table
    @Autowired
    TestRepositoryInterface testRepositoryInterface;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping(path = "/cleantest")
    public void cleanTestTable() {
        testRepositoryInterface.deleteAll();
        jdbcTemplate.update("ALTER TABLE test AUTO_INCREMENT = 0");
    }
}
