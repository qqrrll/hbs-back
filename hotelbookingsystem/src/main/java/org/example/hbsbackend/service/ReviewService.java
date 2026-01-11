package org.example.hbsbackend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.ReviewCreateDTO;
import org.example.hbsbackend.dto.ReviewDTO;
import org.example.hbsbackend.entity.Hotel;
import org.example.hbsbackend.entity.Review;
import org.example.hbsbackend.entity.User;
import org.example.hbsbackend.repository.HotelRepository;
import org.example.hbsbackend.repository.ReviewRepository;
import org.example.hbsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    public List<ReviewDTO> getAllReviews() {
        List<ReviewDTO> reviewDTO = new ArrayList<>();
        for(Review review : reviewRepository.findAll()) {
            reviewDTO.add(ReviewDTO.convertToDTO(review));
        }
        return reviewDTO;
    }
    public ReviewDTO getReviewById(Long id) {
        return ReviewDTO.convertToDTO(reviewRepository.findById(id).get());
    }

    public ReviewDTO createReview(ReviewCreateDTO reviewCreateDTO) {
        User user = userRepository.findById(reviewCreateDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Hotel hotel = hotelRepository.findById(reviewCreateDTO.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel not found"));
        Review review = new Review();
        review.setUser(user);
        review.setHotel(hotel);
        review.setText(reviewCreateDTO.getText());
        review.setRating(reviewCreateDTO.getRating());
        return ReviewDTO.convertToDTO(reviewRepository.save(review));
    }

    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setText(reviewDTO.getText());
        review.setRating(reviewDTO.getRating());
        return ReviewDTO.convertToDTO(reviewRepository.save(review));
    }
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
