package gr.codehub.crminnovative.repository;


import gr.codehub.crminnovative.model.OrderProduct;
import gr.codehub.crminnovative.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {

    //todo
    //JPQL query with JOIN FETCH the lazy associations.





}
