package com.saurabhs.jobms.Job.dto;

import com.saurabhs.jobms.Job.external.Company;
import com.saurabhs.jobms.Job.external.Reviews;

import java.util.List;

public class JobDTO {
//    Instead of whole JSON we can put the fields that we want and this will make the response more structured
private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
    private List<Reviews> reviews;

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }
//    public Job getJob() {
//        return job;
//    }
//
//    public void setJob(Job job) {
//        this.job = job;
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

/*
    @GetMapping("/{id}")
    DTO stands for **Data Transfer Object**, and it's used to **carry data between layers** in an application — especially in web applications or microservices.

Here’s **why DTOs are used**:

---

### 🚛 1. **Efficient Data Transfer**
DTOs are designed to hold **only the data you actually need**. Instead of sending a whole database entity (which may have extra fields or relationships), you send just a lightweight version.

🧠 Example:
Your `User` entity might have 50 fields, but the frontend only needs `username`, `email`, and `profilePicture`. A `UserDTO` can carry just that.

---

### 🔐 2. **Encapsulation and Security**
You might not want to expose internal fields like `password`, `createdAt`, or `role` to external clients. DTOs help you **hide sensitive fields**.

---

### 🎯 3. **Decoupling Layers**
DTOs act as a buffer between your **domain model (e.g., JPA entity)** and your **API layer**. That means your internal models can change without breaking the API contract.

---

### 🔄 4. **Custom Representation**
You can create different DTOs for different use-cases — for example:

- `UserSummaryDTO` (used in a list view)
- `UserDetailDTO` (used in detailed view)
- `UserUpdateDTO` (used during profile update)

Each has fields relevant to the task, not the full model.

---

### 🔁 5. **Avoid Lazy Loading Issues**
When sending JPA entities directly, you might face problems like `LazyInitializationException`. DTOs help avoid this by **flattening nested objects** or **fetching data explicitly**.

---

### ✅ Example

```java
// Entity
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Date createdAt;
    // + more fields
}

// DTO
public class UserDTO {
    private String username;
    private String email;
}
```

---

In short:
> 🔧 **DTOs make your application cleaner, safer, and more efficient** — especially in APIs and microservices.

Let me know if you want examples of converting between Entity ↔ DTO (e.g., using ModelMapper or manually).
 */