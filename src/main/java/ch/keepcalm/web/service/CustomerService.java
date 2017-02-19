package ch.keepcalm.web.service;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);


    private RabbitTemplate rabbitTemplate;
    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository,
                           RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Customer createCustomer(Customer customer) {
        return repository.save(customer);
    }


    public Customer createCustomerEvent(Customer customer) {
        logger.info("Sending an event...");
        String msg = "Customer : " + customer.getFirstname() + " " + customer.getLastname();
        rabbitTemplate.convertAndSend("myQueue", msg);

        return repository.save(customer);
    }

    public void deleteCustomer(Customer customer) {
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
