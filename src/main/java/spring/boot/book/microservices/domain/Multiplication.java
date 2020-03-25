package spring.boot.book.microservices.domain;

public class Multiplication {

    private int factorA;
    private int factorB;

    private int result;

    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }

    public int getFactorA() {
        return factorA;
    }

    public int getFactorB() {
        return factorB;
    }

    public int getResult() {
        return result;
    }

    public String toString() {
        return "Multiplication {" +
                "factorA=" + factorA +
                ", factorB=" + factorB +
                ", result(A*B)=" + result +
                "}";
    }
}