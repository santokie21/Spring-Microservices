package io.noobi.jobapp.review.impl;

import io.noobi.jobapp.company.Company;
import io.noobi.jobapp.company.CompanyService;
import io.noobi.jobapp.review.Review;
import io.noobi.jobapp.review.ReviewRepository;
import io.noobi.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;


    public ReviewServiceImplementation(ReviewRepository reviewRepository,CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService=companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Long companyId, Review review) {
        Company company= companyService.getCompanyById(companyId);
        if(company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews=reviewRepository.findByCompanyId(companyId);
        Review find=reviews.stream()
                .filter
                        (review ->
                                review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
        return find;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
        Company company=companyService.getCompanyById(companyId);
        if(companyId != null) {
            Review review1=getReview(companyId,reviewId);
            if(review1 != null) {
                if(updateReview.getTitle() != null)
                    review1.setTitle(updateReview.getTitle());
                if(updateReview.getDescription() != null)
                    review1.setDescription(updateReview.getDescription());
                if(updateReview.getRating() != null)
                    review1.setRating(updateReview.getRating());
                if(updateReview.getCompany() != null)
                    review1.setCompany(updateReview.getCompany());

                reviewRepository.save(review1);

                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        Company company=companyService.getCompanyById(companyId);
        if( company != null ) {
            boolean existed = reviewRepository.existsById(reviewId);
            if(existed) {
                Review review=reviewRepository.findById(reviewId).orElse(null);
                assert review != null;
                Company company1=review.getCompany();
                company1.getReviews().remove(review);
                review.setCompany(null);
                companyService.updateCompany(companyId,company);
                reviewRepository.deleteById(reviewId);
                return true;
            }
            return false;
        }
        return false;
    }
}
