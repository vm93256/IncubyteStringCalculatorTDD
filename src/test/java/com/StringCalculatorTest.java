package com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {
    StringCalculator stringCalculator;

    @BeforeEach
    public void init() {
        stringCalculator = new StringCalculator();
    }

    @AfterEach
    public void destroy() {
        stringCalculator = null;
    }

    @Test
    public void testSingleAddNumber() {
        assertEquals(0, stringCalculator.add(""));
        assertEquals(1, stringCalculator.add("1"));
        assertEquals(2, stringCalculator.add("2"));
    }

    @Test
    public void testAddMultipleNumbers() {
        assertEquals(7, stringCalculator.add("5,2"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void testNewLine(){
        assertEquals(15, stringCalculator.add("1\n2,3\n4\n5"));
    }

    @Test
    public void testOtherDelimiter() {
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

    @Test
    public void testNegativeNumber() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> stringCalculator.add("-7,7"));
        assertEquals("Negatives not allowed: -7", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> stringCalculator.add("9,-9,8,-8"));
        assertEquals("Negatives not allowed: -9,-8", exception2.getMessage());
    }

    @Test
    public void testNumberValueOverThousand() {
        assertEquals(10, stringCalculator.add("1000,10"));
    }

    @Test
    public void testAddWithMultiCharacterDelimiter() {
        int result = stringCalculator.add("//[***]\n1***2***3");
        assertEquals(6, result);
    }

    @Test
    public void testMultipleDelimiters() {
        assertEquals(6, stringCalculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    public void testMultipleDifferentLengthDelimiters() {
        assertEquals(6, stringCalculator.add("//[***][%]\n1***2%3"));
    }


}