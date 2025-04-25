package com.saurabhs.jobms.Job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//This class is a configuration class that defines a bean for RestTemplate.
//The RestTemplate is a Spring class that provides a convenient way to make HTTP requests to RESTful web services.
//The @Configuration annotation indicates that this class is a configuration class,
//and the @Bean annotation indicates that the method is a bean definition method.

@Configuration
public class AppConfig {

    //@LoadBalanced annotation is used to create a load-balanced RestTemplate bean.
    @LoadBalanced
    //RestTemplate is a class in Spring Framework that provides a convenient way to make HTTP requests to RESTful web services.
    //It is used to communicate with other services in a distributed system.
    //The @LoadBalanced annotation is used to indicate that the RestTemplate bean should be configured to use a load balancer.
    //This means that when the RestTemplate makes a request to another service, it will use a load balancer to distribute the request across multiple instances of the service.
    //This is useful for ensuring that the load on the service is evenly distributed and that the service can handle a high volume of requests.
    //The @Bean annotation is used to indicate that the method should be used to create a bean in the Spring application context.
    //In this case,
    //the RestTemplate bean will be created and configured to use a load balancer.  This bean can then be injected into other classes in the application that need to make HTTP requests to other services.
    //RestTemplate is a class in Spring Framework that provides a convenient way to make HTTP requests to RESTful web services.
    //It is used to communicate with other services in a distributed system.

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

/*
When you're using **Eureka** for service discovery, you **can** use `RestTemplate` — but to actually *leverage* Eureka’s service registry (i.e., call services by their **service name**, not by IP or host), you need to enable **load-balancing** with Eureka integration.

That’s why `RestTemplate` must be configured with a **load balancer** — otherwise, it won't know how to resolve service names like `http://my-service`.

---

### 💥 Why Default `RestTemplate` Doesn't Work

By default, a `RestTemplate` instance **doesn't know anything about Eureka**. It can't resolve service names registered with Eureka — it expects actual URLs like `http://localhost:8080/endpoint`.

So if you try:

```java
restTemplate.getForObject("http://my-service/endpoint", String.class);
```

You'll get an error like:
`UnknownHostException: my-service`.

Because `RestTemplate` doesn’t know how to map `my-service` to an actual IP/port. That's what the **Eureka client + Ribbon** does when you annotate it properly.

---

### ✅ Solution: Annotate RestTemplate with `@LoadBalanced`

To enable Eureka name resolution and client-side load balancing, declare `RestTemplate` as a Spring Bean and annotate it with `@LoadBalanced`.

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

Now, `RestTemplate` will:

- Contact Eureka
- Get list of instances for `my-service`
- Choose one (via Ribbon or Spring Cloud LoadBalancer)
- Forward the request to that instance

---

### 🚀 Summary

| Without `@LoadBalanced` | With `@LoadBalanced` |
|-------------------------|----------------------|
| Expects full URL/IP     | Resolves service names via Eureka |
| No load balancing       | Built-in client-side load balancing |
| No integration with Eureka | Works perfectly with service registry |

Let me know if you want a working example or want to see the equivalent using **WebClient** or **Feign**!
 */
