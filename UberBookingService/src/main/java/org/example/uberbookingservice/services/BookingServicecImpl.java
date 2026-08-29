package org.example.uberbookingservice.services;

import org.example.uberbookingservice.apis.LocationServiceApi;
import org.example.uberbookingservice.apis.UberSocketApi;
import org.example.uberbookingservice.dto.*;
import org.example.uberbookingservice.repositories.BookingRepository;
import org.example.uberbookingservice.repositories.DriverRepository;
import org.example.uberbookingservice.repositories.PassengerRepository;
import org.example.uberprojectentityservice.models.Booking;
import org.example.uberprojectentityservice.models.BookingStatus;
import org.example.uberprojectentityservice.models.Driver;
import org.example.uberprojectentityservice.models.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServicecImpl implements BookingService {


//    private static final String LOCATION_SERVICE= "http://localhost:8080";
    private final LocationServiceApi locationServiceApi;

    private final PassengerRepository passengerRepository;

    private final BookingRepository bookingRepository;

    private final RestTemplate restTemplate;
    private final DriverRepository driverRepository;
    private final UberSocketApi uberSocketApi;

    // Add restTemplate to your existing constructor

    public BookingServicecImpl(PassengerRepository passengerRepository,
                               BookingRepository bookingRepository,
                               RestTemplate restTemplate,
                               LocationServiceApi locationServiceApi, DriverRepository driverRepository, UberSocketApi uberSocketApi) {
        this.passengerRepository = passengerRepository;
        this.bookingRepository = bookingRepository;
        this.restTemplate = restTemplate;
        this.locationServiceApi = locationServiceApi;
        this.driverRepository = driverRepository;
        this.uberSocketApi = uberSocketApi;
    }

    @Override
    public CreateBookingResponseDto createBooking(CreateBookingDto bookingdetails) {
      Optional<Passenger> passenger=  passengerRepository.findById(bookingdetails.getPassengerId());
        Booking booking= Booking.builder()
                .bookingStatus(BookingStatus.ASSIGNING_DRIVER)
                .startLocation(bookingdetails.getStartLocation())
                .endLocation(bookingdetails.getEndLocation())
                .passanger(passenger.get())
                .build();
        Booking newBooking=bookingRepository.save(booking);
        //make an api call to location service to fetch nearby drivers
        NearbyDriverRequestDto request = NearbyDriverRequestDto.builder()
                .latitude(bookingdetails.getStartLocation().getLatitude())
                .longitude(bookingdetails.getStartLocation().getLongitude())
                .build();

        processNearbyDriversAsync(request, bookingdetails.getPassengerId(), newBooking.getId() );

       return CreateBookingResponseDto.builder()
                .bookingId(newBooking.getId())
                .bookingStatus(newBooking.getBookingStatus().toString())
                .build();
    }

    @Override
    public UpdateBookingResponseDto updateBooking(UpdateBookingRequestDTo bookingRequestDTo, Long bookingId) {
            Optional<Driver> driver= driverRepository.findById(bookingRequestDTo.getDriverId().get());
            bookingRepository.updateBookingStatusAndDriverById(bookingId, BookingStatus.SCHEDULED, driver.get());
            Optional <Booking> booking= bookingRepository.findById(bookingId);
            return UpdateBookingResponseDto.builder()
                    .bookingId(bookingId)
                    .status(booking.get().getBookingStatus())
                    .driver(Optional.ofNullable(booking.get().getDriver()))
                    .build();
    }

    private void processNearbyDriversAsync(NearbyDriverRequestDto requestDto, Long passengerId, Long bookingId){
        Call<DriverLocationDto[]> call= locationServiceApi.getNearbyDrivers(requestDto);
        call.enqueue(new Callback<DriverLocationDto[]>() {
            @Override
            public void onResponse(Call<DriverLocationDto[]> call, Response<DriverLocationDto[]> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<DriverLocationDto> driverLocations= Arrays.asList(response.body());
                    driverLocations.forEach(driverLocationDto -> {
                        System.out.println(driverLocationDto.getDriverId() + " " + "lat: " + driverLocationDto.getLatitude() + "Long: " + driverLocationDto.getLongitude());
           });

          try {
                    rasieRideRequestAsync(RideRequestDto.builder().passengerId(passengerId).bookingId(bookingId).build());

          } catch (Exception e) {
              throw new RuntimeException(e);
          }


            }else {
                    System.out.println("Request Failed. Status Code: " + response.code() + " (Could not read error body)");
                }
            }

            @Override
            public void onFailure(Call<DriverLocationDto[]> call, Throwable throwable) {
                throwable.printStackTrace();

            }
        });
    }
    private void rasieRideRequestAsync(RideRequestDto requestDto ) throws IOException {
        Call <Boolean> call= uberSocketApi.raiseRideRequest(requestDto);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.isSuccessful() && response.body() != null){
                    Boolean result = response.body();
                    System.out.println("Driver response is " + result.toString() );


                }else {
                    System.out.println("Request Failed. Status Code: " + response.code() + " (Could not read error body)");
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
