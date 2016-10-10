package ch.keepcalm.web.config;

import ch.keepcalm.web.bootstrap.RogerRabbitMQBootstrap;
import org.springframework.context.annotation.Bean;

/**
 * Created by marcelwidmer on 26.09.16.
 */
//@Configuration
public class RogerRabbitMQConfiguration {

    @Bean
    public RogerRabbitMQBootstrap mqBootstrap() {
        return new RogerRabbitMQBootstrap();
    }
}
