package gr.codehub.crminnovative.service;

import gr.codehub.crminnovative.model.Customer;
import gr.codehub.crminnovative.model.Role;
import gr.codehub.crminnovative.model.SessionUser;
import gr.codehub.crminnovative.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that manages current user details loading
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    CustomerRepository customerRepository;

    /**
     * constructor of this class, correct way to set the autowired attributes
     *
     * @param customerRepository: repository that has access to all the users of the system
     */
    @Autowired
    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * searches for user in the repository
     *
     * @param email takes as input an email, which is unique to every user
     * @return returns a User if he is found in the repository
     * @throws UsernameNotFoundException throws this exception if user is not found in the repository
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //these attributes are required by the Spring User Details class
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        //Search for the user within the repository, and if the user doesn't exist, throw an exception
        Customer repoCustomer =
                customerRepository.findFirstByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
        //Map the authority list with the spring security list
        List grantList = new ArrayList();
        for (Role role : repoCustomer.getRoles()) {
            // ROLE:USER or ROLE:ADMIN or BOTH
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole());
            grantList.add(grantedAuthority);
        }
        SessionUser user = new SessionUser(repoCustomer.getId(), repoCustomer.getEmail(), repoCustomer.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantList);
        return user;
    }
}