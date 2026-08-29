package org.example.uberprojectentityservice.models;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExactLocation  extends BaseModel{
    private double latitude;
    private double longitude;
}
