package ch.keepcalm.web.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;
    private String lastname;

}
