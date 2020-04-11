package spring.boot.book.microservices.service;

import lombok.extern.slf4j.Slf4j;
import spring.boot.book.microservices.domain.Multiplication;
import spring.boot.book.microservices.domain.MultiplicationResultAttempt;
import spring.boot.book.microservices.domain.User;
import spring.boot.book.microservices.event.EventDispatcher;
import spring.boot.book.microservices.event.MultiplicationSolvedEvent;
import spring.boot.book.microservices.repository.MultiplicationResultAttemptRepository;
import spring.boot.book.microservices.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MultiplicationServiceImpl implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;

    private MultiplicationResultAttemptRepository attemptRepository;
    private UserRepository userRepository;
    private EventDispatcher eventDispatcher;

    @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
                                     final MultiplicationResultAttemptRepository attemptRepository,
                                     final UserRepository userRepository
                                   , final EventDispatcher eventDispatcher
    ) {

        this.randomGeneratorService = randomGeneratorService;

        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;

        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public Multiplication createRandomMultiplication() {

        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();

        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt attempt) {

        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

        Assert.isTrue(!attempt.isCorrect(), "채점한 상태로 보낼 수 없습니다!");

        boolean isCorrect = attempt.getResultAttempt() ==
                attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB();

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(user.orElse(attempt.getUser()),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                isCorrect);

        log.info("- attempt Repository ");
        attemptRepository.save(checkedAttempt);
        log.info("- attempt Repository end");

        eventDispatcher.send(new MultiplicationSolvedEvent(
                checkedAttempt.getId(),
                checkedAttempt.getUser().getId(),
                checkedAttempt.isCorrect())
        );

        return isCorrect;
    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(final String userAlias) {

        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }

    @Override
    public MultiplicationResultAttempt getResultById(final Long resultId) {

        // findOne(resultId)
        return attemptRepository.findById(resultId).orElse(null);
    }
}
