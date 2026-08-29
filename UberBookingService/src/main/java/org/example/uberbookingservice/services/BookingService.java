package org.example.uberbookingservice.services;

import org.example.uberbookingservice.dto.CreateBookingDto;
import org.example.uberbookingservice.dto.CreateBookingResponseDto;
import org.example.uberbookingservice.dto.UpdateBookingRequestDTo;
import org.example.uberbookingservice.dto.UpdateBookingResponseDto;
import org.springframework.http.HttpStatus;

public interface BookingService {
    CreateBookingResponseDto createBooking(CreateBookingDto bookingdetails);
    UpdateBookingResponseDto updateBooking(UpdateBookingRequestDTo bookingRequestDTo, Long bookingId);
}
