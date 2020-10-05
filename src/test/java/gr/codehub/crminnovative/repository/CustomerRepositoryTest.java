package gr.codehub.crminnovative.repository;

import gr.codehub.crminnovative.dto.CustomerDto;
import gr.codehub.crminnovative.exception.CustomerCreationException;
import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerRepositoryTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;



    @Test
    public void addCustomerThrowException(){
        Customer customer = new Customer();
        customer.setEmail("giannisklian.com");
        when(customerRepository.save(customer)).thenReturn(customer);


        CustomerDto customerDto = modelMapper.map(customer,CustomerDto.class);
        Exception exception = assertThrows(CustomerCreationException.class,()->{
            customerService.addCustomer(customerDto);
        });
        assertEquals("invalid customer's email",exception.getMessage());
    }

    @Test
    public void addCustomer(){
        Customer customer = new Customer();
        customer.setEmail("giannis@klian.com");
        when(customerRepository.save(customer)).thenReturn(customer);
        try {
            CustomerDto customerDto = modelMapper.map(customer,CustomerDto.class);
            assertEquals(customer,customerService.addCustomer(customerDto));
        } catch (CustomerCreationException e) {
            e.printStackTrace();
        }
    }
}
