package com.saurabhs.jobms.Job;

import com.saurabhs.jobms.Job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//all end points related to the Job
@RestController
public class JobController {
    private JobService jobService; // This has not been initilaized yet

    //  Loosely couple , we are not using new Keyword, it will be instantiated during run time
    public JobController(JobService jobService) {  // Constructor-based injection
        this.jobService = jobService;
    }

    //  IF we hit this URL this method will be called
    @GetMapping("/Jobs")
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    //  Whenever we need to post something
    @PostMapping("/Jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/Jobs/{id}")
    public ResponseEntity<JobWithCompanyDTO> getJobById(@PathVariable Long id){
//        pathvariable assign the variable id to the query value
        JobWithCompanyDTO jobWithCompanyDTO = jobService.getJobById(id);
        if(jobWithCompanyDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jobWithCompanyDTO, HttpStatus.OK);
    }

    @DeleteMapping("/Jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        if(jobService.deleteJobById(id))
            return new ResponseEntity<>("Job Deleted Successfully", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/Jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if(updated){
            return new ResponseEntity<>("Job Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

// ResponseEntity is a special type in Spring Boot that wraps your response and lets you customize:
//✅ HTTP status codes (e.g., 200 OK, 404 NOT FOUND)
//✅ Response body (actual data)
//✅ Response headers (extra metadata)

//@PathVariable is used in Spring Boot to extract values from the URL and use them as method parameters.
//@GetMapping("/users/{userId}/orders/{orderId}")
//public String getOrder(@PathVariable int userId, @PathVariable int orderId) {
//    return "User ID: " + userId + ", Order ID: " + orderId;
//}

//@RequestBody is used in Spring Boot to convert incoming JSON data into a Java object automatically.
//
//🔹 It helps when you're sending JSON data in a POST, PUT, or PATCH request.

//

//  @RequestMapping is an annotation in Spring Boot that maps HTTP requests to specific methods in a controller.
//@RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String createJob() {
//        return "Job Created!";
//    }


//Spring is doing Dependency Injection — meaning, it creates the JobService and injects it into your controller.
//You did not write:
//this.jobService = new JobService(); // ❌ tightly coupled
//That would mean your controller directly creates the service — bad practice!
//Instead, Spring gives you the service — this makes your controller depend on the abstraction of JobService, not its creation.