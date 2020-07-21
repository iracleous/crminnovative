package gr.codehub.crminnovative.controller;


import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class CrmController {
    @Autowired
    @Qualifier ("ImplDB")
    private CustomerService customerService;

    @Autowired
    private OrdersService ordersService;

    @PostMapping("orders")
    public Orders createOrders(){
        return ordersService.createOrder(null);
    }


    @RequestMapping("hello")
    public String getHello(){
        return "<b>Hello</b>";
    }

    @GetMapping("customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping("add/{name}" )
    public Customer addCustomer(@PathVariable String name){
        Customer customer = new Customer();
        customer.setFirst(name);

        return customerService.addCustomer(customer);
    }

    @PostMapping("addcustomer" )
    public Customer addCustomerAllDetails(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("remove/{id}")
    public boolean deleteCustomer(@PathVariable int id){
        return customerService.deleteCustomer(id);
    }

}
