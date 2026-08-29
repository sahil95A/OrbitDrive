package org.example.uberprojectentityservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.Date;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(columnList = "driver_id")
})
public class Booking extends BaseModel {

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endtTime;

    private Long totalDistance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passanger;

    @OneToOne(cascade = CascadeType.ALL )
    private ExactLocation startLocation;

    @OneToOne(cascade = CascadeType.ALL )
    private ExactLocation endLocation;
}
