package com.saurabhs.jobms.Job.impl;


import com.saurabhs.jobms.Job.Job;
import com.saurabhs.jobms.Job.JobRepository;
import com.saurabhs.jobms.Job.JobService;
import com.saurabhs.jobms.Job.dto.JobDTO;
import com.saurabhs.jobms.Job.external.Company;
import com.saurabhs.jobms.Job.external.Reviews;
import com.saurabhs.jobms.Job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//With this class will be available during runtime, when required and inject that to controller
@Service
public class JobServiceImpl implements JobService {
//    private List<Job> Jobs = new ArrayList<>(); // Later fetch from DataBase

    //    Now use DataBase
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    private Long nextId = 1L;

//    Loosely Couple
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();

//        RestTemplate restTemplate = new RestTemplate();

        for (Job job : jobs) {
            Company company = restTemplate.getForObject("http://company-service:8082/companies/" + job.getCompanyId(), Company.class);
//            What has happened here is that we have created a new instance of RestTemplate and then we are using that instance to make a GET request to the company-service.
//            When we need to get the List of something, we need to use the exchange method. (better)
            ResponseEntity<List<Reviews>> reviewResponse = restTemplate.exchange("http://review-service:8083/reviews?companyId=" + job.getCompanyId(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Reviews>>() {
            });

            List<Reviews> reviews = reviewResponse.getBody();

            JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);
            jobDTO.setCompany(company);
            jobDTOS.add(jobDTO);
        }
        return jobDTOS;
    }

    private JobDTO convertToDTO(Job job) {
        Company company = restTemplate.getForObject("http://company-service:8082/companies/" + job.getCompanyId(), Company.class);
        ResponseEntity<List<Reviews>> reviewResponse = restTemplate.exchange("http://review-service:8083/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Reviews>>() {
                });
        List<Reviews> rev = reviewResponse.getBody();
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDTO(job, company, rev);
        jobDTO.setCompany(company);
        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        assert job != null;
        return convertToDTO(job);
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


// Unknown Host exception
//Rest template is not load balanced
