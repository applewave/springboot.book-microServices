package spring.boot.book.microservices.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public final class Multiplication {

    @Id
    @GeneratedValue
    @Column(name="MULTIPLICATION_ID")
    private Long id;

    private final int factorA;
    private final int factorB;

    // JSON/JPA 역직렬화를 위한 빈 생성자
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
