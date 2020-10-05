package gr.codehub.crminnovative.repository;

import gr.codehub.crminnovative.dto.SurveyResults;

import gr.codehub.crminnovative.exception.ProductNotFoundException;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.OrderProduct;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.model.Product;
import gr.codehub.crminnovative.service.ProductService;
import gr.codehub.crminnovative.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductRepositoryTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;




    /*@Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductService productService;

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

@Test
    void find2Test(){
    List<SurveyResults> survey = productRepository.findSurveyCount();
    int pc= survey.size();
    assertEquals(3, pc);
}

    @Test
    void find3Test() throws ProductNotFoundException {

        List<Product> products = productService.getProducts("potatoes",
                "1.4", "", "");
        int pc= products.size();
        assertEquals(6, pc);
    }*/

}