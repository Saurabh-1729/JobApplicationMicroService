package com.saurabhs.jobms.Job;

import java.util.List;

// As interface for loose coupling
// Define methods in interface and implement them in class
public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
