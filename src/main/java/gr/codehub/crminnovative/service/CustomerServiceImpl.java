package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.dto.CustomerDto;
import gr.codehub.crminnovative.exception.CustomerCreationException;
import gr.codehub.crminnovative.exception.CustomerNotFoundException;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Qualifier("ImplDB")
public class CustomerServiceImpl implements CustomerService {
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(element -> modelMapper.map(element, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto)
            throws CustomerCreationException {

        if (customerDto == null)
            throw new CustomerCreationException("null customer");
         if (customerDto.getEmail()==null  || !customerDto.getEmail().contains("@")    )
            throw new CustomerCreationException("invalid customer's email");



        Customer customer = modelMapper.map(customerDto,Customer.class);

        return modelMapper.map(customerRepository.save(customer),CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, int customerId)
            throws CustomerNotFoundException {

            Customer customerInDb = customerRepository.findById(customerId)
                     .orElseThrow(
                             () -> new CustomerNotFoundException("not such customer"));



        customerInDb = modelMapper.map(customerDto,Customer.class);

        customerRepository.save(customerInDb);

        return modelMapper.map(customerRepository.save(customerInDb),CustomerDto.class);
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
    public CustomerDto getCustomer(int customerId)
            throws CustomerNotFoundException {
        Optional<Customer> oCustomer=
          customerRepository.findById(customerId);
        if (oCustomer.isPresent())
            return modelMapper.map(customerRepository.save(oCustomer.get()),CustomerDto.class);
        else throw new CustomerNotFoundException("not such Customer");
    }
}
