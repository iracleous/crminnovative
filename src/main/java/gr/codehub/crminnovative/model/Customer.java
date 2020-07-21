package gr.codehub.crminnovative.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String first;
    private String last;
    private String street;
    private String number;
    private String vatnumber;
    private String email;
    private Date dob;
    private Date registration;




}
