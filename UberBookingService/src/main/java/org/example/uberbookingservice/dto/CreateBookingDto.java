package org.example.uberbookingservice.dto;

import lombok.*;
import org.example.uberprojectentityservice.models.ExactLocation;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookingDto {
    private Long passengerId;
    private ExactLocation startLocation;
    private ExactLocation endLocation;


}
