package spring.boot.book.microservices.service;

import spring.boot.book.microservices.domain.Multiplication;

public interface MultiplicationService {

    /**
     * 두 개의 무작의 인수를 인수로 담은 {@link Multiplication} 객체를 생성한다
     * */
    Multiplication createRandomMultiplication();
}
