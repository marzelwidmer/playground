package ch.keepcalm.web.controller;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@Controller
public class CustomerController {


    CustomerService service;
    @Autowired
    public CustomerController(CustomerService service){
        this.service = service;
    }

    @GetMapping(value = "customers")
    public ModelAndView getCustomers(){
        final List<Customer> customers = service.getCustomers();
        return new ModelAndView("customers")
                .addObject("customers",  customers);
    }

}
