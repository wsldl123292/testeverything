package guava.eventbus.config;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * User: Bill Bejeck
 * Date: 4/26/13
 * Time: 3:48 PM
 */
@Configuration
@ComponentScan(basePackages = {"guava.eventbus.publisher.simple",
        "guava.eventbus.subscriber.simple"})
public class SimpleEventBusConfig {
    @Bean
    public EventBus eventBus() {
        return new EventBus();
    }
}
