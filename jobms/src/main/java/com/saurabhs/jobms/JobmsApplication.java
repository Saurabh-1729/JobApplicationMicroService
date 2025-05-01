package com.saurabhs.jobms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JobmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobmsApplication.class, args);
	}

}

/*
 In **layman terms**, OpenFeign is like a **smart helper** that makes it super easy for your service to **talk to other services** in a microservices setup.

### 🔍 Without Feign:
You use `RestTemplate`, build the URL manually, send the request, handle errors, etc. It's like writing a letter by hand every time.

### 🚀 With OpenFeign:
You just **create a simple Java interface**, add a few annotations, and Feign **does the rest** — it knows how to call another service, where it is (via Eureka), what to send, and how to handle the response.

---

### 📦 Example:

```java
@FeignClient(name = "company-service")
public interface CompanyClient {
    @GetMapping("/companies/{id}")
    Company getCompanyById(@PathVariable Long id);
}
```

Now, instead of writing all the `RestTemplate` code, you just do:

```java
Company company = companyClient.getCompanyById(job.getCompanyId());
```

---

### ✅ Benefits in plain terms:
- **Less code** (no manual URL or HTTP handling).
- **Easier to read and use**.
- **Works with Eureka** to auto-locate services.
- **Built-in error handling and retries** (if configured).

Want a visual analogy for it?
 */