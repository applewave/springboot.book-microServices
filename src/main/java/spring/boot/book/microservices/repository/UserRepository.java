package spring.boot.book.microservices.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.book.microservices.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByAlias(final String alias);
}
