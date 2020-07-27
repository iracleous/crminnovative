package gr.codehub.crminnovative.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

//@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;
    private int inventoryQuantity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    private List<OrderProduct> orderProducts;

}
