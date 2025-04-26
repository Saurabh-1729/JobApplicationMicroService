package com.saurabhs.jobms.Job.mapper;

import com.saurabhs.jobms.Job.Job;
import com.saurabhs.jobms.Job.dto.JobDTO;
import com.saurabhs.jobms.Job.external.Company;
import com.saurabhs.jobms.Job.external.Reviews;

import java.util.List;

public class JobMapper {
    public static JobDTO mapToJobWithCompanyDTO(Job job, Company company, List<Reviews> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
