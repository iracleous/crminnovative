package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.exception.CustomerCreationException;
import gr.codehub.crminnovative.exception.CustomerNotFoundException;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("ImplDB")
public class CustomerServiceDbImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(Customer customer)
            throws CustomerCreationException {
        if (customer == null)
            throw new CustomerCreationException("null customer");
         if (customer.getEmail()==null  || !customer.getEmail().contains("@")    )
            throw new CustomerCreationException("invalid customer's email");



        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, int customerId)
            throws CustomerNotFoundException {

            Customer customerInDb = customerRepository.findById(customerId)
                     .orElseThrow(
                             () -> new CustomerNotFoundException("not such customer"));

        customerInDb.setFirst(customer.getFirst());
        customerInDb.setLast(customer.getLast());
//
        customerRepository.save(customerInDb);

        return customerInDb;
    }

    /**
     * @param customerIndex the index of the customer to delete
     * @return true on success
     */
    @Override
    public boolean deleteCustomer(int customerIndex) {
        customerRepository.deleteById(customerIndex);
        return true;
    }

    @Override
    public Customer getCustomer(int customerId)
            throws CustomerNotFoundException {
        Optional<Customer> oCustomer=
          customerRepository.findById(customerId);
        if (oCustomer.isPresent()) return
                oCustomer.get();
        else throw new CustomerNotFoundException("not such Customer");
    }
}
