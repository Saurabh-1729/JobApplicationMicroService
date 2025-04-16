package com.saurabhs.jobms.Job.impl;


import com.saurabhs.jobms.Job.Job;
import com.saurabhs.jobms.Job.JobRepository;
import com.saurabhs.jobms.Job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//With this class will be available during runtime, when required and inject that to controller
@Service
public class JobServiceImpl implements JobService {
//    private List<Job> Jobs = new ArrayList<>(); // Later fetch from DataBase

    //    Now use DataBase
    JobRepository jobRepository;
    private Long nextId = 1L;

//    Loosely Couple
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try{
            jobRepository.deleteById(id);
            return true;
        }
        catch(Exception e){
            return false;
        }
//        should have returned a boolean value instead of a whole Class.
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
//      Optional<Job> is used to safely handle the case where a Job might not exist in the database, and to avoid null pointer errors in a clean, readable way.
        if(jobOptional.isPresent()){
                Job job = jobOptional.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getLocation());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                jobRepository.save(job);
                return true;
        }
        return false;
    }
}
