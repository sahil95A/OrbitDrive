package org.example.uberbookingservice.apis;

import org.example.uberbookingservice.dto.DriverLocationDto;
import org.example.uberbookingservice.dto.NearbyDriverRequestDto;
import org.example.uberbookingservice.dto.RideRequestDto;
import org.springframework.http.ResponseEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UberSocketApi {

    @POST("/api/socket/newride")
    Call<Boolean> raiseRideRequest(@Body RideRequestDto requestDto);
}