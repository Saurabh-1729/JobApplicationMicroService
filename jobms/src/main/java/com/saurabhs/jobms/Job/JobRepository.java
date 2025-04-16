package com.saurabhs.jobms.Job;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long> { // Object and Id

}

//This will create a JobRepo that is extending jparepo and doing all the work, I just need to provide ID and Key
