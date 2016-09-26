package ch.keepcalm.web.service;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@Service
public class CustomerService {

    private CustomerRepository repostitory;
    @Autowired
    public CustomerService(CustomerRepository repostitory){
        this.repostitory = repostitory;
    }

    public Customer createCustomer(Customer model){
        return repostitory.save(model);
    }
}
