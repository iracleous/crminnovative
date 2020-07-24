package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.exception.*;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.OrderProduct;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrdersServiceImplTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdersService orderService;


//    @Test
//        void addProductToOrdersTest(){
//
//        orderService.addProductToOrders(2,"")
//
//    }




    @Test
    void testOrderScenario() throws CustomerCreationException,
            ProductCreationException, CustomerNotFoundException, ProductNotFoundException, CannotCreateOrderException {
        Customer customer = new Customer();
        customer.setFirst("John");
        customer.setEmail("john@gmail.com");
        customerService.addCustomer(customer);

        Product product = new Product();
        product.setName("potatoes");
        productService.addProduct(product);

        Orders order =
                orderService.createOrder(customer.getId());
        OrderProduct orderProduct =
                orderService.addProductToOrders(
                        product.getId(), order.getId());

        List<OrderProduct> orderProductList =
                orderService.getOrder(order.getId())
                        .getOrderProducts();

        assertEquals(1, orderProductList.size());
    }
}