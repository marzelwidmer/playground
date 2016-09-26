package ch.keepcalm.web.controller;

import ch.keepcalm.web.model.Customer;
import ch.keepcalm.web.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by marcelwidmer on 26.09.16.
 */
//@RestController
//@RequestMapping(value = "/api/customers", produces = "application/hal+json")
public class CustomerController {


    CustomerService service;

    @Autowired
    public CustomerController(CustomerService service){
        this.service = service;
    }

   /* @GetMapping
    public ResponseEntity getCustomers() {
        return new ResponseEntity("customers")
                .addObject("customers", repository.findAll())
                .addObject("createLink", linkTo(
                        methodOn(CustomerController.class).createCustomer(null, null))
                        .withRel("Create")
                );
    }*/
   @GetMapping
   public ResponseEntity getCustomers() {
      /* final ModelAndView modelAndView = new ModelAndView("customers")
               .addObject("customers", service.getCustomers())
               .addObject("createLink", linkTo(
                       methodOn(CustomerController.class).createCustomer(null))
                       .withRel("Create")
               );*/

       return new ResponseEntity<>(service.getCustomers(), HttpStatus.FOUND);
   }
    @PostMapping
    public ResponseEntity createCustomer(@RequestBody Customer customer) {
        final Customer result = service.createCustomer(customer);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
