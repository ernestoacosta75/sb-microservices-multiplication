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

    @Bean
    public Hibernate5Module hibernateModule() {
        return new Hibernate5Module();
    }
}
