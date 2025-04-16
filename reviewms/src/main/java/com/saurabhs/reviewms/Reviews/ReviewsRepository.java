package com.saurabhs.reviewms.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findByCompanyId(Long companyId); // method will be implemented during run Time, the functionality will be implemented by name
}
