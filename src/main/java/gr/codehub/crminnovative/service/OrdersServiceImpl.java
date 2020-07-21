package gr.codehub.crminnovative.service;


import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService{
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public Orders createOrder(Customer customer) {
        return ordersRepository.save(new Orders());
    }
}
