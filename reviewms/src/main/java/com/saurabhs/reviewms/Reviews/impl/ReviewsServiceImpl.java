package com.saurabhs.reviewms.Reviews.impl;


import com.saurabhs.reviewms.Reviews.Reviews;
import com.saurabhs.reviewms.Reviews.ReviewsRepository;
import com.saurabhs.reviewms.Reviews.ReviewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;

    public ReviewsServiceImpl(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    @Override
    public List<Reviews> getAllReviews(Long companyId) {
        return reviewsRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Reviews reviews, Long companyId) {
//        get the company
//        Company company = companyService.getCompanyById(companyId);
          if(companyId != null && reviews != null){
              reviews.setCompanyId(companyId);
              reviewsRepository.save(reviews);
              return true;
          }
//        since review is not coming with company we have to set it
        else
            return false;
    }

    @Override
    public Reviews getReview(Long reviewId) {
        return reviewsRepository.findById(reviewId).orElse(null);
//        return reviews.stream().filter(reviews1 -> reviews1.getId().equals(reviewId)).findFirst().orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Reviews updatedReviews) {
//        if(companyService.getCompanyById(companyId) != null){
//            updatedReviews.setCompany(companyService.getCompanyById(companyId));
//            updatedReviews.setId(reviewId);
//            reviewsRepository.save(updatedReviews);
//            return true;
//        }
//        return false;
            Reviews reviews = reviewsRepository.findById(reviewId).orElse(null);
            if(reviews != null){
                reviews.setTitle(updatedReviews.getTitle());
                reviews.setDescription(updatedReviews.getDescription());
                reviews.setCompanyId(updatedReviews.getCompanyId());
                reviews.setRating(updatedReviews.getRating());
                reviewsRepository.save(reviews);
                return true;
            }
            return false;

    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Reviews reviews = reviewsRepository.findById(reviewId).orElse(null);
        if(reviews != null){
            reviewsRepository.delete(reviews);
            return true;
        }
        return false;
    }
}
