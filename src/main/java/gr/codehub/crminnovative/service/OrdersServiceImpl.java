package gr.codehub.crminnovative.service;


import gr.codehub.crminnovative.exception.CustomerNotFoundException;
import gr.codehub.crminnovative.exception.ProductNotFoundException;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.model.OrdersProduct;
import gr.codehub.crminnovative.model.Product;
import gr.codehub.crminnovative.repository.CustomerRepository;
import gr.codehub.crminnovative.repository.OrdersProductRepository;
import gr.codehub.crminnovative.repository.OrdersRepository;
import gr.codehub.crminnovative.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

    private OrdersRepository ordersRepository;
    private CustomerRepository customerRepository;
    private OrdersProductRepository ordersProductRepository;
    private ProductRepository productRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository,
                             CustomerRepository customerRepository,
                             OrdersProductRepository ordersProductRepository,
                             ProductRepository productRepository
    ) {
        this.ordersRepository = ordersRepository;
        this.customerRepository = customerRepository;
        this.ordersProductRepository = ordersProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Orders createOrder(int customerId)
            throws CustomerNotFoundException {
        Orders orders = new Orders();
        Customer customerFromDb =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(() -> new
                                CustomerNotFoundException("Cannot find Customer"));
        orders.setCustomer(customerFromDb);

        return ordersRepository.save(orders);
    }

    @Override
    public Orders getOrder(UUID OrdersId) throws CustomerNotFoundException {
        return ordersRepository.findById(OrdersId).orElseThrow(() -> new
                CustomerNotFoundException("Cannot find Customer"));
    }

    @Override
    public OrdersProduct addProductToOrders(int productId, UUID OrdersId)
            throws ProductNotFoundException, CustomerNotFoundException {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new
                        ProductNotFoundException("Cannot find product"));

        Orders orders = ordersRepository.findById(OrdersId).orElseThrow(() -> new
                CustomerNotFoundException("Cannot find Customer"));

        OrdersProduct ordersProduct = new OrdersProduct();
        ordersProduct.setProduct(product);
        ordersProduct.setOrders(orders);

        return ordersProductRepository.save(ordersProduct);
    }


    //TODO
    //to be implemented with JPQL
    @Override
    public List<OrdersProduct> getProductsFromOrders(UUID OrdersId)
            throws ProductNotFoundException {
        return
                ordersProductRepository
                        .findAll()
                        .stream()
                        .filter(po -> po.getOrders().getId().equals(OrdersId))
                        .collect(Collectors.toList());

    }
}
