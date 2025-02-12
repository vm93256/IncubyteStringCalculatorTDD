package com;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {

    @Test
    public void testSingleAddNumber() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add(""));
        assertEquals(1, stringCalculator.add("1"));
        assertEquals(2, stringCalculator.add("2"));
    }

    @Test
    public void testAddMultipleNumbers() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(7, stringCalculator.add("5,2"));
    }

    @Test
    public void testEmptyString() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void testNewLine(){
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(15, stringCalculator.add("1\n2,3\n4\n5"));
    }

    @Test
    public void testOtherDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

}