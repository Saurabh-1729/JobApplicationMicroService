package com.saurabhs.reviewms.Reviews;

import java.util.List;

public interface ReviewsService {
    List<Reviews> getAllReviews(Long companyId);
    boolean addReview(Reviews reviews, Long companyId);
    Reviews getReview(Long reviewId);
    boolean updateReview(Long reviewId, Reviews reviews);

    boolean deleteReview(Long reviewId);
}
