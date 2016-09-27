package ch.keepcalm.web.repository;

import ch.keepcalm.web.model.Checkout;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marcelwidmer on 26.09.16.
 */
public interface CheckoutRepository extends CrudRepository <Checkout, String>{
}