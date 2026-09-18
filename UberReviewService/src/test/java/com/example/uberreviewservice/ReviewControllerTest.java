package com.example.uberreviewservice;

import com.example.uberreviewservice.adapters.CreateReviewDtoToReviewAdapter;
import com.example.uberreviewservice.controllers.ReviewController;
import com.example.uberreviewservice.models.Review;
import com.example.uberreviewservice.services.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @Mock
    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindReviewById_Success(){
        long reviewId= 1L;
        Review mockReview= Review.builder().build();
        mockReview.setId(reviewId);

        //mocking----------------
        when(reviewService.findReviewById(reviewId)).thenReturn(Optional.of(mockReview));

        ResponseEntity<?> response= reviewController.findReviewById(reviewId);

        //assertion-----------
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Optional<Review> returnedReview = (Optional<Review>) response.getBody();
        assertEquals(reviewId ,returnedReview.get().getId());
    }
}
