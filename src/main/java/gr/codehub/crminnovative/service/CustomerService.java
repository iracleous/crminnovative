package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.dto.CustomerDto;
import gr.codehub.crminnovative.exception.CustomerCreationException;
import gr.codehub.crminnovative.exception.CustomerNotFoundException;
import gr.codehub.crminnovative.model.Customer;

import java.util.List;




public interface CustomerService {
    List<CustomerDto> getCustomers();
    CustomerDto addCustomer(CustomerDto customer) throws CustomerCreationException;
    CustomerDto updateCustomer(CustomerDto customer, int customerId)
            throws CustomerNotFoundException;
    boolean deleteCustomer(int customerIndex);
    CustomerDto getCustomer(int customerId) throws CustomerNotFoundException;

}
