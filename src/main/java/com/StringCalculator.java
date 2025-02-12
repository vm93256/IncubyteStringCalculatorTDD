package com;

import java.util.Arrays;

public class StringCalculator {

    public int add(String numbers) {
        int length = numbers.length();
        if (length < 2) {
            if (numbers.isEmpty()) {
                return 0;
            } else {
                return convertToInt(numbers);
            }
        }else {
            String delimiter = ",|\n";
            return sum(splitNumbers(numbers, delimiter));
        }
    }

    public int convertToInt(String num){
        return Integer.parseInt(num);
    }

    private String[] splitNumbers(String numbers, String divider) {
        return numbers.split(divider);
    }

    private int sum(String[] numbers) {
        return Arrays.stream(numbers)
                .mapToInt(this::convertToInt)
                .sum();
    }
}
