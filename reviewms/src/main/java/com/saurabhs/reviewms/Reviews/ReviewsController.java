package com.saurabhs.reviewms.Reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {
    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @GetMapping
    public ResponseEntity<List<Reviews>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewsService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody  Reviews reviews, @RequestParam Long companyId){
        boolean isReviewSaved = reviewsService.addReview(reviews, companyId);
        if(isReviewSaved)
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review Not Saved", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Reviews> getReview(@PathVariable Long reviewId){
//      Need to add check here
        return new ResponseEntity<>(reviewsService.getReview(reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Reviews reviews){
        boolean isReviewUpdated = reviewsService.updateReview(reviewId, reviews);
        if(isReviewUpdated)
            return new ResponseEntity<>("Review Updated Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review Not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId){
        boolean isReviewDeleted = reviewsService.deleteReview(reviewId);
        if(isReviewDeleted)
            return new ResponseEntity<>("Review Deleted Successfully", HttpStatus.OK);
        return new ResponseEntity<>("Review Not Found", HttpStatus.NOT_FOUND);
    }
}


// We are trying to get rid of the companyID, as it is a microservice, and it should not depend on other microservices.
//We will take the company as query parameter
// RequestBody(Json Data), PathVariable(// kark ), RequestParam(?variable=variablename)
