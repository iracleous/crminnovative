package gr.codehub.crminnovative.controller;


import gr.codehub.crminnovative.exception.CustomerCreationException;
import gr.codehub.crminnovative.exception.CustomerNotFoundException;
import gr.codehub.crminnovative.exception.ProductCreationException;
import gr.codehub.crminnovative.exception.ProductNotFoundException;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.model.OrdersProduct;
import gr.codehub.crminnovative.model.Product;
import gr.codehub.crminnovative.service.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
public class CrmController {

    private CustomerService customerService;
    private OrdersService ordersService;
    private ProductService productService;



    public CrmController(@Qualifier("ImplDB") CustomerService customerService
            , OrdersService ordersService,  ProductService productService            ) {
        this.customerService = customerService;
        this.ordersService = ordersService;
        this.productService = productService;
    }

    @GetMapping("customer")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

      @GetMapping("customer/{id}")
      public Customer getCustomer(@PathVariable int id)
              throws CustomerNotFoundException {
           return customerService.getCustomer(id);
      }

    @PostMapping("customer")
    public Customer addCustomer(@RequestBody Customer customer)
            throws CustomerCreationException {
        return customerService.addCustomer(customer);
    }

    @PutMapping("customer/{id}" )
    public Customer updateCustomer(@RequestBody Customer customer,
            @PathVariable int id  ) throws CustomerNotFoundException {
        return customerService.updateCustomer(customer, id);
    }

    @DeleteMapping("customer/{id}")
    public boolean deleteCustomer(@PathVariable int id){
        return customerService.deleteCustomer(id);
    }

    @GetMapping("product")
    public List<Product> getProducts(){
        return productService.getProducts();
    }
    @PostMapping("product")
    public Product addProduct(@RequestBody Product product)
            throws ProductCreationException {
        return productService.addProduct(product);
    }

   @PostMapping("orders/{customerId}")
    public Orders createOrders(@PathVariable int customerId)
           throws CustomerNotFoundException {
        return ordersService.createOrder(customerId);
   }

    @PostMapping("buy/{ordersId}/{productId}")
    public OrdersProduct createOrdersProduct(@PathVariable UUID ordersId,
                                             @PathVariable int  productId              )
            throws CustomerNotFoundException, ProductNotFoundException {
        return ordersService.addProductToOrders(productId, ordersId);
    }
//
//
//    @GetMapping("customers")
//    public List<Customer> getCustomers(){
//        return customerService.getCustomers();
//    }
//
//    @PostMapping("add/{name}" )
//    public Customer addCustomer(@PathVariable String name){
//        Customer customer = new Customer();
//        customer.setFirst(name);
//
//        return customerService.addCustomer(customer);
//    }
//
//    @PostMapping("addcustomer" )
//    public Customer addCustomerAllDetails(@RequestBody Customer customer){
//        return customerService.addCustomer(customer);
//    }
//
//    @DeleteMapping("remove/{id}")
//    public boolean deleteCustomer(@PathVariable int id){
//        return customerService.deleteCustomer(id);
//    }

}
