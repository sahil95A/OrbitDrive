package org.example.uberbookingservice.apis;

import org.example.uberbookingservice.dto.DriverLocationDto;
import org.example.uberbookingservice.dto.NearbyDriverRequestDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationServiceApi {

    @POST("/api/location/nearby/drivers")
    Call<DriverLocationDto[]> getNearbyDrivers(@Body NearbyDriverRequestDto requestDto);
}
