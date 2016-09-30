package ch.keepcalm.web.controller;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView getCustomer() {
        return new ModelAndView("customers")
                .addObject("customer", new Customer())
                .addObject("createLink", linkTo(
                        methodOn(CustomerController.class).createCustomer(null, null))
                        .withRel("Create")
                );
    }

    // FIXME: 29.09.16 https://github.com/spring-projects/spring-hateoas/issues/471
    //@PostMapping(value = "/customers/create")
    @RequestMapping(value = "/customers/created", method = RequestMethod.POST)
    public ModelAndView createCustomer(@Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("customers")
                    .addObject("createLink", linkTo(
                            methodOn(CustomerController.class).createCustomer(null, null))
                            .withRel("Create")
                    );
        }
        service.createCustomer(customer);

        return new ModelAndView("created-customer")
                .addObject("customers", service.getCustomers())
                .addObject("overview", linkTo(
                        methodOn(CustomerController.class).getCustomer())
                        .withRel("Overview")
                );
    }

    // FIXME: 29.09.16 https://github.com/spring-projects/spring-hateoas/issues/471
    // @GetMapping(value = "/customers")
    @RequestMapping(value = "/customers/list", method = RequestMethod.GET)
    public ModelAndView getCustomers() {
        return new ModelAndView("customers")
                .addObject("customers", service.getCustomers())
                .addObject("createLink", linkTo(
                        methodOn(CustomerController.class).createCustomer(null, null))
                        .withRel("Create")
                );
    }
}
