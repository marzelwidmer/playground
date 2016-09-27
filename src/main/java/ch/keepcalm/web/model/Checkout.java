package ch.keepcalm.web.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by marcelwidmer on 26.09.16.
 */
//@Entity
@Data
public class Checkout {

   /* @Id
    @GeneratedValue
    private Long id;*/

    @Id
    private String id;

    //@OneToOne
    @DBRef
    private Customer customer;

}
