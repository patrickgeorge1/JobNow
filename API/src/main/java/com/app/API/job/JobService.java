package com.app.API.job;

import com.app.API.category.Category;
import com.app.API.commons.SearchCriteria;
import com.app.API.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllAdmin() {
        return new ArrayList<>(jobRepository.findAll());
    }

    public List<Job> getAll() {
        List<Job> jobs = getAllAdmin();
        for (Job job : jobs) {
            job.setEmployer(null);
            job.setEmplyee(null);
        }
        return jobs;
    }

    public void addJob(User emplyer, Category category, Float price, String title, String description, Double latitude, Double longitude) {
        Job new_job = new Job((long) 0, emplyer, null, category, price, title, description, 0, new Date(), latitude, longitude);
        jobRepository.save(new_job);
    }

    public List getFilteredAdmin(Map<String, ArrayList<String>> criteria) {
        ArrayList<JobSpecification> conditions = new ArrayList<>();
        for (String key: criteria.keySet()) {
            ArrayList<String> parameters = criteria.get(key);
            JobSpecification conditon = new JobSpecification(new SearchCriteria(key, parameters.get(0), parameters.get(1)));
            conditions.add(conditon);
        }


        Specification specification = Specification.where(conditions.get(0));
        for (JobSpecification c: conditions.subList(1, conditions.size())) {
            System.out.println(c.toString());
            specification =  specification.and(c);
        }

        return jobRepository.findAll(specification);
    }

    public List getFiltered(Map<String, ArrayList<String>> criteria) {
        List<Job> jobs = getFilteredAdmin(criteria);
        for (Job job: jobs) {
            job.setEmployer(null);
            job.setEmplyee(null);
        }
        return jobs;
    }

}
