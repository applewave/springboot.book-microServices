package spring.boot.book.microservices.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {

    private static int MINIMUN_FACTOR = 11;
    private static int MAXIMUN_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAXIMUN_FACTOR - MINIMUN_FACTOR) + 1) + MINIMUN_FACTOR;
    }
}
