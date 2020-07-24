package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.exception.CannotCreateOrderException;
import gr.codehub.crminnovative.exception.CustomerNotFoundException;
import gr.codehub.crminnovative.exception.ProductNotFoundException;
import gr.codehub.crminnovative.model.Orders;
import gr.codehub.crminnovative.model.OrderProduct;

import java.util.List;
import java.util.UUID;

public interface OrdersService {
    //todo
    //can i have private members or fields

    Orders createOrder(int customerId)
            throws CustomerNotFoundException,
            CannotCreateOrderException;

    Orders getOrder(UUID OrdersId)
            throws CustomerNotFoundException;

    OrderProduct addProductToOrders(
            int productId, UUID OrdersId)
            throws ProductNotFoundException,
            CustomerNotFoundException;

    List<OrderProduct> getProductsFromOrders(UUID OrdersId)
            throws ProductNotFoundException;

}
