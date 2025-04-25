package com.saurabhs.jobms.Job;

import com.saurabhs.jobms.Job.dto.JobWithCompanyDTO;

import java.util.List;

// As interface for loose coupling
// Define methods in interface and implement them in class
public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    JobWithCompanyDTO getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
