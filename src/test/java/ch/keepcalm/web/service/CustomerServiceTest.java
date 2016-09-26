package ch.keepcalm.web.service;

import ch.keepcalm.web.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@ActiveProfiles(profiles = {"junit"})
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService service;

    @Test
    public void saveCustomer() {
        final Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        final Customer customerResult = service.createCustomer(customer);
        Assert.assertEquals("John", customerResult.getFirstname());
        // clean db
        service.getCustomers().forEach(c -> service.deleteCustomer(c));
    }

    @Test
    public void saveAndDeleteCustomer() {
        // create a customer
        final Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        service.createCustomer(customer);
        // delete a customer
        service.deleteCustomer(customer);
        final Customer customerResult = service.getCustomer(customer.getId());
        Assert.assertNull(customerResult);
        // clean db
        service.getCustomers().forEach(c -> service.deleteCustomer(c));
    }

    @Test
    public void getCustomers() {
        final Customer customer1 = new Customer();
        customer1.setFirstname("Jack");
        customer1.setLastname("Jackson");
        final Customer customer2 = new Customer();
        customer2.setFirstname("John");
        customer2.setLastname("Doe");
        // create a customers
        service.createCustomer(customer1);
        service.createCustomer(customer2);
        // count customers in db
        Assert.assertEquals(2, service.getCustomers().size());
        // clean db
        service.getCustomers().forEach(c -> service.deleteCustomer(c));
        // count customers in db after cleanUp
        Assert.assertEquals(0, service.getCustomers().size());
    }
}
