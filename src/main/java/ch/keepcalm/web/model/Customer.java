package ch.keepcalm.web.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by marcelwidmer on 26.09.16.
 */
//@Entity
@Data
public class Customer {

/*
    @Id
    @GeneratedValue
    private Long id;
*/
    @Id
    private String id;

    private String firstname;
    private String lastname;

}
