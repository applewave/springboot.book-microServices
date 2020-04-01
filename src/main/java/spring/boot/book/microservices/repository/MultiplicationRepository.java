package spring.boot.book.microservices.repository;

import org.springframework.data.repository.CrudRepository;
import spring.boot.book.microservices.domain.Multiplication;

public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}
