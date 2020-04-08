package spring.boot.book.microservices.service;

import spring.boot.book.microservices.domain.Multiplication;
import spring.boot.book.microservices.domain.MultiplicationResultAttempt;

import java.util.List;

public interface MultiplicationService {

    /**
     * 두 개의 무작의 인수를 인수로 담은 {@link Multiplication} 객체를 생성한다
     * */
    Multiplication createRandomMultiplication();

    /**
     * @return 곱샘 계산 결과가 맞으면 true, 아니면 false
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

    List<MultiplicationResultAttempt> getStatsForUser(String userAlias);

    MultiplicationResultAttempt getResultById(Long resultId);
}
