package com.app.API.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepositoryInterface testRepositoryInterface;

    public List<Test> getAllTests()  {
        List<Test> tests = new ArrayList<>();
        testRepositoryInterface.findAll().forEach(tests::add);
        return tests;
    }

    public Test getTest(Long id) {
        return testRepositoryInterface.findById(id).get();
    }

    public void addTest(Test test) {
        testRepositoryInterface.save(test);
    }

    public void deleteTest(Long id) {
        testRepositoryInterface.deleteById(id);
    }
}
