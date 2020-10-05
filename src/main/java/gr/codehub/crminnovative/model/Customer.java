package gr.codehub.crminnovative.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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
    private String password;
    private LocalDate dob;
    private LocalDate registration;

    @ManyToOne
    private Customer recommender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="recommender")
    private List<Customer> recommended;


    @JsonIgnore
    @OneToMany( mappedBy = "customer")
    private List<Orders> orders;


    @ManyToMany(fetch = FetchType.EAGER)
    //user owns the association, role does not own the association
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "costumer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;



}
