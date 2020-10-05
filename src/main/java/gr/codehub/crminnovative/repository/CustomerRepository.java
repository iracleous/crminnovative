package gr.codehub.crminnovative.repository;

import gr.codehub.crminnovative.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {

    Optional<Customer> findFirstByEmail(String emailAddress);
    List<Customer> findByLastName(String lastname, Sort sort);
    Page<Customer> findByFirstName(String firstname, Pageable pageable);


}
