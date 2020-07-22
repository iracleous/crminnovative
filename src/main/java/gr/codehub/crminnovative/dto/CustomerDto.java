package gr.codehub.crminnovative.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int id;

    private String first;

    private String last;
    private String street;
    private String number;
    private String vatNumber;
    private String email;
    private Date date;
    private Date registration;
}
