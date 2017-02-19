package ch.keepcalm.web.controller;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(value = "/api/customer")
    public ResponseEntity getCustomers(){
        List<Customer> customers = service.getCustomers();
        return new ResponseEntity(customers, HttpStatus.FOUND);
    }
    @PostMapping(value = "/api/customer")
    public ResponseEntity saveCustomer(@RequestBody Customer customer){
        Customer result = service.createCustomer(customer);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/api/customer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable String id){
        Customer customer = service.getCustomer(id);
        if (customer!= null){
            service.deleteCustomer(customer);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }







    // FIXME: 29.09.16 https://github.com/spring-projects/spring-hateoas/issues/471
    // @GetMapping(value = "/customers")
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
    @RequestMapping(value = "/customers", method = RequestMethod.POST)
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

    @RequestMapping(value = "/customers/created", method = RequestMethod.GET)
    public ModelAndView getCustomers(@Valid Customer customer) {
        return new ModelAndView("created-customer")
                .addObject("customers", service.getCustomers())
                .addObject("overview", linkTo(
                        methodOn(CustomerController.class).getCustomer())
                        .withRel("Overview")
                );
    }
}
