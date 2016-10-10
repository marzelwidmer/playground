package ch.keepcalm.web.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by marcelwidmer on 26.09.16.
 */
//@Entity
@Data
public class Customer implements Serializable{

    private static final long serialVersionUID = 6270246497130573556L;
    /*
        @Id
        @GeneratedValue
        private Long id;
    */
    @Id
    private String id;

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String address;
    @NotBlank
    private String city;
    @NotBlank
    private String zip;


}
