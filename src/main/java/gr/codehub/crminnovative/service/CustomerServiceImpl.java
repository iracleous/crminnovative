package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private static List<Customer>
            customers = new ArrayList<>();

    static {

        Customer customer = new Customer();
        customer.setFirst("Giannis");
        customers.add(customer);

        Customer customer1 = new Customer();
        customer1.setFirst("Elissavet");
        customers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setFirst("Vangelis");
        customers.add(customer2);

    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customers.get(customers.size() - 1);
    }

    @Override
    public boolean deleteCustomer(int customerIndex) {
        if (customerIndex >= 0 && customerIndex < customers.size()) {
            customers.remove(customerIndex);
            return true;
        }
        return false;
    }
}
