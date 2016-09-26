package ch.keepcalm.web.config;

import ch.keepcalm.web.bootstrap.DatabaseBootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by marcelwidmer on 26.09.16.
 */
@Configuration
public class DevConfiguration {

    @Bean
    public DatabaseBootstrap databaseBootstrap(){
        return new DatabaseBootstrap();
    }
}
