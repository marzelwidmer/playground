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
        final Customer customerResult = service.createCustomer(Customer.builder().firstname("John").name("Doe").build());
        Assert.assertEquals("John", customerResult.getFirstname());
        // clean db
        service.getCustomers().forEach(c -> service.deleteCustomer(c));
    }

    @Test
    public void saveAndDeleteCustomer() {
        // create a customer
        final Customer customerModel = Customer.builder().firstname("Jack").name("Jackson").build();
        service.createCustomer(customerModel);
        // delete a customer
        service.deleteCustomer(customerModel);
        final Customer customerResult = service.getCustomer(customerModel.getId());
        Assert.assertNull(customerResult);
        // clean db
        service.getCustomers().forEach(c -> service.deleteCustomer(c));
    }

    @Test
    public void getCustomers() {
        // create a customers
        service.createCustomer(Customer.builder().firstname("Jack").name("Jackson").build());
        service.createCustomer(Customer.builder().firstname("John").name("Doe").build());
        // count customers in db
        Assert.assertEquals(2, service.getCustomers().size());
        // clean db
        service.getCustomers().forEach(c -> service.deleteCustomer(c));
        // count customers in db after cleanUp
        Assert.assertEquals(0, service.getCustomers().size());
    }
}
