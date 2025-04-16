package com.saurabhs.jobms.Job;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity // Map this class to a table Entity (means a row)
//@Table(name = "job _table") // This will change the database default name
public class Job {
    @Id //This tells it is primary Key
//    @GeneratedValue(strategy =  GenerationType.IDENTITY)  // Id is automatically created, we don't have to manage
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;

//    Default Constructor is required for JPA
    public Job() {
    }


//    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.minSalary = minSalary;
//        this.maxSalary = maxSalary;
//        this.location = location;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}


//When JPA reads data from the database (like a row from a table), it needs to create an instance of your class (like Job).
//It does that using something called reflection, and for that… it needs a no-arg constructor (a constructor with no parameters).