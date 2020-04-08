package spring.boot.book.microservices.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.book.microservices.domain.MultiplicationResultAttempt;

import java.util.List;

public interface MultiplicationResultAttemptRepository
        extends CrudRepository<MultiplicationResultAttempt, Long> {

    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
