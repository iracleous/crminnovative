package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.exception.ProductCreationException;
import gr.codehub.crminnovative.exception.ProductNotFoundException;
import gr.codehub.crminnovative.model.Product;

import java.util.List;

// to be converted using generics

public interface ProductService {

    List<Product> getProducts();
    Product addProduct(Product product) throws ProductCreationException;
    Product updateProduct(Product product, int productId)
            throws ProductNotFoundException;
    boolean deleteProduct(int productIndex);
    Product getProduct(int productId) throws ProductNotFoundException;
    
}
