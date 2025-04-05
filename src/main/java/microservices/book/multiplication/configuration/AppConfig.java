package microservices.book.multiplication.configuration;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class AppConfig {
    @Bean
    public Random random() {
        return new Random();
    }

    /**
     * Since entities like User will be fetched in LAZY mode,
     * and because Hibernate creates proxies for our classes in runtime.
     * we have to use a specific module for Hibernate that we can use in
     * the ObjectMapper objects and create a bean for the new Hibernate module
     * for Jackson.
     * Spring Boot's Jackson2ObjectMapperBuilder will use it via autoconfiguration,
     * and all the ObjectMapper instances will use the Spring Boot defaults plus
     * our own customization.
     * @return
     */
    @Bean
    public Hibernate5Module hibernateModule() {
        return new Hibernate5Module();
    }
}
