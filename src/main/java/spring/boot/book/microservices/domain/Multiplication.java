package spring.boot.book.microservices.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class Multiplication {

    private final int factorA;
    private final int factorB;

    Multiplication() {
        this(0, 0);
    }
//    private int result;
//    public Multiplication(int factorA, int factorB) {
//        this.factorA = factorA;
//        this.factorB = factorB;
//        this.result = factorA * factorB;
//    }

//    public int getFactorA() {
//        return factorA;
//    }

//    public int getFactorB() {
//        return factorB;
//    }

//    public int getResult() {
//        return result;
//    }

//    public String toString() {
//        return "Multiplication {" +
//                "factorA=" + factorA +
//                ", factorB=" + factorB +
//                ", result(A*B)=" + result +
//                "}";
//    }
}
