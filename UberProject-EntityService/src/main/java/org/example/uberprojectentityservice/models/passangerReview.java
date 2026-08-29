package org.example.uberprojectentityservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class passangerReview extends Review{
    @Column(nullable = false)
    private String passangerReviewComment;
    @Column(nullable = false)
    private String passangerRating;
}
