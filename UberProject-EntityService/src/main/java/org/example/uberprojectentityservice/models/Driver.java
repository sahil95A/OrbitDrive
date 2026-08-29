package org.example.uberprojectentityservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bookings"})
public class Driver extends BaseModel {
    private String name;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    private String phoneNumber;

    private String aadharCard;

    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL)
    private Car car;

    @Enumerated(value = EnumType.STRING)
    private DriverApprovalStatus driverApprovalStatus;

    @OneToOne
    private ExactLocation lastKnownLocation;

    @OneToOne
    private ExactLocation home;

    private String activeCity;

    @DecimalMin(value = "0.01", message = "rating must be less than or equal to 0")
    @DecimalMax(value = "5.00", message = "rating must be less than or equal to 5")
    private Double rating;

    //1 : n, driver has many booking
    @OneToMany(mappedBy = "driver")
    @Fetch(FetchMode.SUBSELECT)
    private List<Booking> bookings;

    private boolean is_available;
}
