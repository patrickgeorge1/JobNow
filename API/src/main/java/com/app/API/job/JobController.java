package com.app.API.job;

import com.app.API.category.Category;
import com.app.API.category.CategoryRepository;
import com.app.API.user.User;
import com.app.API.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.*;


@RestController
@CrossOrigin
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(path = "/api/public/admin/jobs", method = RequestMethod.GET)
    public @ResponseBody List<Job> getAllJobsAdmin() {
        return jobService.getAllAdmin();
    }

    @RequestMapping(path = "/api/public/jobs", method = RequestMethod.GET)
    public @ResponseBody List<Job> getAllJobs() { return jobService.getAll(); }

    @RequestMapping(path = "/api/public/jobs", method = RequestMethod.POST)
    public @ResponseBody ObjectNode addJob(@RequestBody ObjectNode json) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        try {
            User emplyer = userRepository.findByUsername((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Category category = categoryRepository.findByName(json.get("category").asText());
            Float price = ((Double)json.get("price").asDouble()).floatValue();
            String title = json.get("title").asText();
            String description = json.get("description").asText();
            Double latitude = json.get("latitude").asDouble();
            Double longitude = json.get("longitude").asDouble();

            jobService.addJob(emplyer, category, price, title, description, latitude, longitude);
            objectNode.put("status", "success");
        } catch (Exception e) {
            objectNode.put("status", "failed");
        }
        return objectNode;
    }

    @RequestMapping(path = "/api/public/admin/jobs/filtered", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List getFilteredJobsAdmin(@RequestBody Map<String, Object> json) {
        Map<String, ArrayList<String>> filters = (HashMap<String, ArrayList<String>>)json.get("filters");
        return jobService.getFilteredAdmin(filters);
    }



    @RequestMapping(path = "/api/public/jobs/filtered", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List getFilteredJobs(@RequestBody Map<String, Object> json) {
        Map<String, ArrayList<String>> filters = (HashMap<String, ArrayList<String>>)json.get("filters");
        return jobService.getFiltered(filters);
    }
 }
