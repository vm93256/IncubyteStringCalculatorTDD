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
            String delimiter = ",";
            if (numbers.matches("//(.*)\n(.*)")) {
                delimiter = Character.toString(numbers.charAt(2));
                numbers = numbers.substring(4);
                System.out.println(numbers);
            }

            return sum(splitNumbers(numbers, delimiter + "|\n"));
        }
    }

    public int convertToInt(String num){
        return Integer.parseInt(num);
    }

    private String[] splitNumbers(String numbers, String divider) {
        return numbers.split(divider);
    }

    private int sum(String[] numbers) {
        int total = 0;
        StringBuilder negativeString = new StringBuilder();

        for (String number : numbers) {
            if (convertToInt(number) < 0) {
                if (negativeString.toString().equals(""))
                    negativeString = new StringBuilder(number);
                else
                    negativeString.append(",").append(number);
            }
            total += convertToInt(number);
        }

        if (!negativeString.toString().equals("")) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeString);
        }

        return total;
    }
}
