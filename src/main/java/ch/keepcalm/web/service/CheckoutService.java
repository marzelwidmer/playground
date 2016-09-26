package ch.keepcalm.web.service;

import ch.keepcalm.web.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@Service
public class CheckoutService {

    private CheckoutRepository repository;
    @Autowired
    public CheckoutService(CheckoutRepository repository){
        this.repository = repository;
    }


}
