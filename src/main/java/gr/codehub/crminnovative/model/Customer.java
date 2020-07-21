package gr.codehub.crminnovative.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer  {

    private String name;
    private String address;
    private int age;
    private Date date;

    public Customer(String name){
        this.name=name;
    }

}