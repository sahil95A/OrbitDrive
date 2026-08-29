package org.example.uberbookingservice.Controllers;

import org.example.uberbookingservice.dto.CreateBookingDto;
import org.example.uberbookingservice.dto.CreateBookingResponseDto;
import org.example.uberbookingservice.dto.UpdateBookingRequestDTo;
import org.example.uberbookingservice.dto.UpdateBookingResponseDto;
import org.example.uberbookingservice.repositories.BookingRepository;
import org.example.uberbookingservice.services.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<CreateBookingResponseDto> createBooking(@RequestBody CreateBookingDto createBookingDto) {

        return new ResponseEntity<>(bookingService.createBooking(createBookingDto), HttpStatus.CREATED);
    }

    @PostMapping("/{bookingId}")
    public ResponseEntity<UpdateBookingResponseDto> updateBooking(@RequestBody UpdateBookingRequestDTo requestDto, @PathVariable Long bookingId) {
        return new ResponseEntity<>(bookingService.updateBooking(requestDto, bookingId), HttpStatus.OK);
    }
}