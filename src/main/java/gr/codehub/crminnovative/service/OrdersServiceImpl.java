package gr.codehub.crminnovative.service;


import gr.codehub.crminnovative.exception.CannotCreateOrderException;
import gr.codehub.crminnovative.exception.CustomerNotFoundException;
import gr.codehub.crminnovative.exception.ProductNotFoundException;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.model.OrderProduct;
import gr.codehub.crminnovative.model.Product;
import gr.codehub.crminnovative.repository.CustomerRepository;
import gr.codehub.crminnovative.repository.OrdersProductRepository;
import gr.codehub.crminnovative.repository.OrdersRepository;
import gr.codehub.crminnovative.repository.ProductRepository;
import gr.codehub.crminnovative.util.CustomerUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

    private static final int DEFAULT_ORDER_SIZE = 1;
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
            throws CustomerNotFoundException,
            CannotCreateOrderException {
        Orders orders = new Orders();
        Customer customerFromDb =
                customerRepository
                        .findById(customerId)
                        .orElseThrow(() -> new
                                CustomerNotFoundException("Cannot find Customer"));

       if( customerFromDb.getDob()==null ||
               CustomerUtil.getAge( customerFromDb.getDob())<18)
           throw new CannotCreateOrderException("Customer too young or no dob data");

        orders.setCustomer(customerFromDb);
        orders.setOrdersDate(new Date());
        return ordersRepository.save(orders);
    }

    @Override
    public Orders getOrder(UUID OrdersId) throws CustomerNotFoundException {
        return ordersRepository.findById(OrdersId).orElseThrow(() -> new
                CustomerNotFoundException("Cannot find Customer"));
    }

    @Override
    public OrderProduct addProductToOrders(int productId, UUID OrdersId)
            throws ProductNotFoundException, CustomerNotFoundException {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new
                        ProductNotFoundException("Cannot find product"));
        Orders orders = ordersRepository.findById(OrdersId).orElseThrow(() -> new
                CustomerNotFoundException("Cannot find Customer"));

        Optional<OrderProduct> ordersProductOptional=
                ordersProductRepository.findAll()
                .stream()
                .filter( op-> op.getOrders().getId().equals(OrdersId) &&
                        op.getProduct().getId()==productId)
                .findFirst();


        OrderProduct orderProduct;
        if (ordersProductOptional.isPresent()) {
            orderProduct = ordersProductOptional.get();
            orderProduct.setQuantity(  orderProduct.getQuantity()+DEFAULT_ORDER_SIZE );
        }
        else {
            orderProduct = new OrderProduct();
            orderProduct.setQuantity(DEFAULT_ORDER_SIZE);
            orderProduct.setPrice(product.getPrice());
        }
        Product productInOrder = orderProduct.getProduct();
        productInOrder. setInventoryQuantity(
                productInOrder. getInventoryQuantity()-DEFAULT_ORDER_SIZE );
        productRepository.save(productInOrder);
        ordersProductRepository.save(orderProduct);
        return orderProduct;
    }


    //TODO
    //to be implemented with JPQL
    @Override
    public List<OrderProduct> getProductsFromOrders(UUID OrdersId)
            throws ProductNotFoundException {
        return
                ordersProductRepository
                        .findAll()
                        .stream()
                        .filter(po -> po.getOrders().getId().equals(OrdersId))
                        .collect(Collectors.toList());

    }
}
