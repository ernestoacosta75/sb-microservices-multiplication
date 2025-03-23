package microservices.book.multiplication.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAlias(final String alias);
}
