package main.services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service
public class GenerateNumSequencesService {

    private final int SEQ_COUNT = 5;

    public List<Set<Integer>> getRandomSeq(int count) {

        List<Set<Integer>> result = new ArrayList<>();

        for (int i = 0; i < SEQ_COUNT; i++) {
            result.add(getSequence(count));
        }

        return result;
    }

    private Set<Integer> getSequence(int count) {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while (set.size() < count) {
            int value = Math.abs(random.nextInt());
            if (isPrimeNumber(value))
                set.add(value);
        }
        return set;
    }

    private boolean isPrimeNumber(int number) {
        BigInteger bigInteger = BigInteger.valueOf(number);
        return bigInteger.isProbablePrime((int) Math.log(number));
    }

}
