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
        final Customer customerModel = Customer.builder().firstname("Marcel").name("Widmer").build();
        final Customer customerResult = service.createCustomer(customerModel);
        Assert.assertEquals("Marcel", customerResult.getFirstname());
    }
}
