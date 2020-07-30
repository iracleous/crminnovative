package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.exception.ProductCreationException;
import gr.codehub.crminnovative.exception.ProductNotFoundException;
import gr.codehub.crminnovative.model.Product;
import gr.codehub.crminnovative.repository.ProductRepository;
import gr.codehub.crminnovative.repository.specs.ProductSpecification;
import gr.codehub.crminnovative.repository.specs.SearchCriteria;
import gr.codehub.crminnovative.repository.specs.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //TODO
    //check the paging method
    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }



    @Override
    public List<Product> getProducts (String productName,
                                      String lessThanPrice,
                                      String lessThanQuantity,
                                      String moreThanQuantity) {

        String hello="hello to you";
        log.info("Simple log statement with inputs {} ", hello );



        ProductSpecification msTitleRating = new ProductSpecification();
        msTitleRating.add(new SearchCriteria("name", productName, SearchOperation.MATCH));
        msTitleRating.add(new SearchCriteria("price", lessThanPrice, SearchOperation.GREATER_THAN));
        List<Product> msTitleRatingList = productRepository.findAll(msTitleRating);
    return msTitleRatingList;


    }

//    @Autowired
//    EntityManager em;
//
//    @Override
//    public List<Product> getProducts (String productName,
//                                                String lessThanPrice,
//                                                String lessThanQuantity,
//                                                String moreThanQuantity) {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
//
//        Root<Product> product = cq.from(Product.class);
//        Predicate authorNamePredicate = cb.equal(product.get("price"), lessThanPrice);
//        Predicate titlePredicate = cb.like(product.get("name"), "%" + productName + "%");
//        cq.where(authorNamePredicate, titlePredicate);
//
//        TypedQuery<Product> query = em.createQuery(cq);
//        return query.getResultList();
//    }




//    @Override
//    public List<Product> getProducts(String productName,
//                                     String lessThanPrice,
//                                     String lessThanQuantity,
//                                     String moreThanQuantity) throws ProductNotFoundException {
//
//
//        String hello="hello to you";
//        log.info("Simple log statement with inputs {} ", hello );
//
//
//        if (productName != null)
//            return productRepository.findByName(productName);
//        if (lessThanPrice != null)
//            return productRepository.findByPriceLessThan(
//                    Double.parseDouble(lessThanPrice));
//        if (moreThanQuantity != null)
//            return productRepository.findByInventoryQuantityGreaterThan(
//                    Integer.parseInt(moreThanQuantity));
//
//        return productRepository.findAll();
//    }

    @Override
    public Product addProduct(Product product) throws ProductCreationException {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, int productId) throws ProductNotFoundException {
        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(int productIndex) {
        productRepository.deleteById(productIndex);
        return true;
    }

    @Override
    public Product getProduct(int productId) throws ProductNotFoundException {
        return null;
    }
}
