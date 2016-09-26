package ch.keepcalm.web.repository;

import ch.keepcalm.web.model.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by marcelwidmer on 26.09.16.
 */
public interface CustomerRepository extends CrudRepository <Customer, Long>{
}