package io.codelex;

import java.util.*;

public class Main {

    private static final int AGE_PRODUCT = 36;

    public static void main(String[] args) {
        List<ChildAges> possibleSolutions = findPossibleSolutions();
        List<Integer> ageSum = calculateSumOfChildrenAges(possibleSolutions);
        int houseNumber = findHouseNumber(ageSum);
        ChildAges correctAges = findCorrectAges(possibleSolutions, houseNumber);
        System.out.println("Children ages are: " + correctAges.age1() + ", " + correctAges.age2() + ", " + correctAges.age3());
    }

    private static List<ChildAges> findPossibleSolutions() {
        List<ChildAges> possibleSolutions = new ArrayList<>();
        for (int age1 = 1; age1 <= AGE_PRODUCT; age1++) {
            if (AGE_PRODUCT % age1 == 0) {
                for (int age2 = age1; age2 <= AGE_PRODUCT / age1; age2++) {
                    if (AGE_PRODUCT % (age1 * age2) == 0) {
                        int age3 = AGE_PRODUCT / (age1 * age2);
                        if (age1 <= age2 && age2 <= age3) {
                            possibleSolutions.add(new ChildAges(age1, age2, age3));
                        }
                    }
                }
            }
        }
        return possibleSolutions;
    }

    private static int findHouseNumber(List<Integer> sums) {
        return sums.stream()
                .filter(sum -> Collections.frequency(sums, sum) > 1)
                .distinct()
                .findFirst()
                .orElse(-1);
    }

    private static List<Integer> calculateSumOfChildrenAges(List<ChildAges> solutions) {
        return solutions.stream()
                .map(ChildAges::getSum)
                .toList();
    }

    private static ChildAges findCorrectAges(List<ChildAges> solutions, int houseNumber) {
        for (ChildAges ages : solutions) {
            if (ages.getSum() == houseNumber && ages.hasEldestSon()) {
                return ages;
            }
        }
        return new ChildAges(-1, -1, -1);
    }
}