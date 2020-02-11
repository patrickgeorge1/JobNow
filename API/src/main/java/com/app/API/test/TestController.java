package com.app.API.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

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
