package gr.codehub.crminnovative.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // @Column(nullable=false)
    private String firstName;

    private String lastName;
    private String street;
    private String number;
    private String vatNumber;
    private String email;
    private LocalDate dob;
    private LocalDate registration;

    @ManyToOne
    private Customer recommender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="recommender")
    private List<Customer> recommended;


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private List<Orders> orders;



}
