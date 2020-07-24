package gr.codehub.crminnovative.util;

import gr.codehub.crminnovative.model.Customer;

import java.time.LocalDate;

public class CustomerUtil {

    public static int getAge(LocalDate dob){
        return LocalDate.now().getYear() - dob.getYear();
    }

}
