package gr.codehub.crminnovative.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int id;

    private String firstName;

    private String lastName;
    private String street;
    private String number;
    private String vatNumber;
    private String email;
    private LocalDate dob;
    private LocalDate registration;
}
