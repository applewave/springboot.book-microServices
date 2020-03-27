package spring.boot.book.microservices.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spring.boot.book.microservices.domain.Multiplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class MultiplicationServiceImplTest {

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @Before
    public void setUP() {
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService);
    }

    @Test
    public void createRandomMultiplicationTest() {
        // given
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

        // assert
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }
}
