package org.example.hbsbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.ReviewCreateDTO;
import org.example.hbsbackend.dto.ReviewDTO;
import org.example.hbsbackend.entity.Review;
import org.example.hbsbackend.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;
    @GetMapping("get-all-reviews")
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }
    @GetMapping("get-review-by-id/{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }
    @PostMapping("create-review")
    public ReviewDTO createReview(@RequestBody ReviewCreateDTO review) {
        return reviewService.createReview(review);
    }
    @PutMapping("update-review/{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(id, reviewDTO);
    }
    @DeleteMapping("delete-review/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
    @GetMapping("/hotel/{hotelId}")
    public List<Review> getReviewsByHotel(@PathVariable Long hotelId) {
        return reviewService.getReviewsByHotelId(hotelId);
    }
}
