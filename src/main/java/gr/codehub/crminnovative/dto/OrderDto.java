package gr.codehub.crminnovative.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Date ordersDate;
    private double totalAmount;
}
