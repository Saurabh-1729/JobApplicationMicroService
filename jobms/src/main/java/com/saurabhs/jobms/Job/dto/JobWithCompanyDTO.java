package com.saurabhs.jobms.Job.dto;

import com.saurabhs.jobms.Job.Job;
import com.saurabhs.jobms.Job.external.Company;

public class JobWithCompanyDTO {
    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

//To give response of both company and job, we need to create a DTO
//So using rest template we can get the company and job and send it to the user
