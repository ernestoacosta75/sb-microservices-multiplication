package microservices.book.multiplication.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "USERS")
/**
 * Hibernate uses proxies (like ByteBuddyInterceptor) for lazy-loading entities.
 * Jackson (which Spring Boot uses to serialize JSON) tries to serialize everything,
 * but the Hibernate proxy class (ByteBuddyInterceptor) can't be serialized because
 * it's not a real object - it's a proxy wrapper.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String alias;

    public User(final String userAlias) {
        this(null, userAlias);
    }
}
