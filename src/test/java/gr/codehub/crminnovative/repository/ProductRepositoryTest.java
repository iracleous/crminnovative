package gr.codehub.crminnovative.repository;

import gr.codehub.crminnovative.model.OrderProduct;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    void findByName() {
        List<Integer> products =
        productRepository.findQuantityByName("potatoes");
        assertEquals(6,products.size() );
    }

//    @Test
//    void findByName2() {
//
//        Orders myOrder = ordersRepository.findAll().get(0);
//
//        List<OrderProduct> orders =
//                ordersRepository.findAllOrderProducts(myOrder.getId());
//
//assertEquals(1, orders.size());
//
//    }


}