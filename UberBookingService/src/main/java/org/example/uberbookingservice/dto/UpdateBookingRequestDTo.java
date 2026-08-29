package org.example.uberbookingservice.dto;

import lombok.*;
import org.example.uberprojectentityservice.models.BookingStatus;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookingRequestDTo {

    private String status;
    private Optional<Long> driverId;
}
