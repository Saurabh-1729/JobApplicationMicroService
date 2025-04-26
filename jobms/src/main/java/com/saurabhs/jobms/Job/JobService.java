package com.saurabhs.jobms.Job;

import com.saurabhs.jobms.Job.dto.JobDTO;

import java.util.List;

// As interface for loose coupling
// Define methods in interface and implement them in class
public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);
    JobDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
