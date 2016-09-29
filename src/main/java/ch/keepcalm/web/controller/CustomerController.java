package ch.keepcalm.web.controller;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@Controller
public class CustomerController {


    CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }


    // FIXME: 29.09.16 https://github.com/spring-projects/spring-hateoas/issues/471
    // @GetMapping(value = "/customers")
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView getCustomers() {
        return new ModelAndView("customers")
                .addObject("customers", service.getCustomers())
                .addObject("createLink", linkTo(
                        methodOn(CustomerController.class).createCustomer(null, null))
                        .withRel("Create")
                );
    }


    // FIXME: 29.09.16 https://github.com/spring-projects/spring-hateoas/issues/471
    //@PostMapping(value = "/customers/create")
    @RequestMapping(value = "/customers/create", method = RequestMethod.POST)
    public ModelAndView createCustomer(String firstname, String lastname) {
        Customer customer = new Customer();
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        service.createCustomer(customer);

        return new ModelAndView("created-customer")
                .addObject("overview", linkTo(
                        methodOn(CustomerController.class).getCustomers())
                        .withRel("Overview")
                );
    }

}
