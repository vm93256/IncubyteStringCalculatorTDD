package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";

        if (numbers.matches("//.\n.*")) {
            delimiter = Character.toString(numbers.charAt(2));
            numbers = numbers.substring(4);
        }
        else if (numbers.matches("//\\[.*\\]\n.*")) {
            Matcher matcher = Pattern.compile("//(\\[.*?])+\\n").matcher(numbers);
            if (matcher.find()) {
                String delimitersSection = matcher.group();
                List<String> delimiters = new ArrayList<>();

                Matcher delimiterMatcher = Pattern.compile("\\[(.*?)]").matcher(delimitersSection);
                while (delimiterMatcher.find()) {
                    delimiters.add(Pattern.quote(delimiterMatcher.group(1))); 
                }

                delimiter = String.join("|", delimiters);
                numbers = numbers.substring(matcher.end());
            }
        }

        return sum(splitNumbers(numbers, delimiter));
    }

    public int convertToInt(String num){
        return Integer.parseInt(num);
    }

    private String[] splitNumbers(String numbers, String divider) {
        System.out.println(divider);
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
            if (convertToInt(number) < 1000) total += convertToInt(number);
        }

        if (!negativeString.toString().equals("")) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeString);
        }

        return total;
    }
}
