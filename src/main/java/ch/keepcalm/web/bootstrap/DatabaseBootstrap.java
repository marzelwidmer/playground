package ch.keepcalm.web.bootstrap;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by marcelwidmer on 26.09.16.
 */
public class DatabaseBootstrap implements InitializingBean {

    private static Logger log = LoggerFactory.getLogger(DatabaseBootstrap.class);

    CustomerRepository repository;

    @Autowired
    public void setDatabaseBootstrap(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (repository.findByFirstnameAndLastname("John", "Doe") == null) {
            Customer customer = new Customer();
            customer.setFirstname("John");
            customer.setLastname("Doe");
            customer.setAddress("825 Washington Ave");
            customer.setCity("Miami Beach");
            repository.save(customer);
            log.info("Customer John Doe is creaded.");
        }
        log.info("Bootstrap finish initialized.");
    }
}
