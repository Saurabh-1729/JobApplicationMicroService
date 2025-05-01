package com.saurabhs.jobms.Job.clients;

import com.saurabhs.jobms.Job.external.Reviews;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="review-service")
public interface ReviewsClient {
    @GetMapping("/reviews")
    List<Reviews> getReviews(@RequestParam("companyId") Long companyId);
}
