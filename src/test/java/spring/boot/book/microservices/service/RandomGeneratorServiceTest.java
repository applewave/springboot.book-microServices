package spring.boot.book.microservices.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RandomGeneratorServiceTest {

    @Autowired
    private RandomGeneratorService randomGeneratorService;

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception {

        // given
        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i-> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        // then
        assertThat(randomFactors)
                .containsOnlyElementsOf(IntStream.range(11,100)
                .boxed()
                .collect(Collectors.toList()));
    }
}
