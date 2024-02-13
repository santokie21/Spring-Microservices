package io.noobi.jobapp.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        if (reviews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(reviews);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@PathVariable Long companyId,@RequestBody Review body) {
        boolean added=reviewService.createReview(companyId,body);
        if(added) {
            return new ResponseEntity<>("Review added successfully!",HttpStatus.CREATED);
        }
        return new ResponseEntity<>("No Company found with company id: " + companyId,HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewBytId(@PathVariable Long companyId,@PathVariable Long reviewId) {
        Review review=reviewService.getReview(companyId,reviewId);
        if(review != null) {
            return ResponseEntity.ok(review);
        }
        Review dummy=new Review(0L,"No review","Review not found",0.0,null);
        return new ResponseEntity<>(dummy,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId,@RequestBody Review body) {
        boolean updated=reviewService.updateReview(companyId,reviewId,body);
        if(updated) {
            return ResponseEntity.ok("Review updated Successfully!");
        }
        return new ResponseEntity<>("Review not updated!",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReview(companyId, reviewId);
        if(deleted) {
            return ResponseEntity.ok("Review Deleted Successfully!");
        }
        return new ResponseEntity<>("Review not Deleted!!",HttpStatus.NOT_FOUND);
    }
}
