package ch.keepcalm.web.service;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@Service
public class CustomerService {

    private CustomerRepository repository;
    @Autowired
    public CustomerService(CustomerRepository repository){
        this.repository = repository;
    }

    public Customer createCustomer(Customer customer){
        return repository.save(customer);
    }

    public void deleteCustomer(Customer customer){
        repository.delete(customer);
    }

    public Customer getCustomer(String id) {
        return repository.findOne(id);
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        repository.findAll().forEach(c -> customers.add(c));
        return customers;
    }
}
