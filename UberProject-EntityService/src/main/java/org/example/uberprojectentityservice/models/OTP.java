package org.example.uberprojectentityservice.models;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Random;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OTP extends BaseModel {

    private String code;

    private String sentToNumber;
    //return random otp
    public static OTP make(String phoneNumber){
        Random random = new Random();
        Integer code= random.nextInt(9000)+1000;
        return OTP.builder().code(code.toString()).sentToNumber(phoneNumber).build();
    }

}
