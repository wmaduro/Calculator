package com.vector.client;

import com.vector.calculator.Calculator;
import com.vector.client.exception.InvalidExpressionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CalculatorSimpleClientTest {

    private final CalculatorSimpleClient calculatorSimpleClient = new CalculatorSimpleClient(new Calculator());


    @Test
    @DisplayName("Must parse valid expression successfully")
    public void must_ParseSuccessfully_when_ExpressionIsValid() throws InvalidExpressionException {
        String expression =  "5+5";
        String[] expectedResult = new String[] {"5", "+", "5"};
        String[] elements = calculatorSimpleClient.parseExpression(expression);
        assertArrayEquals(elements, expectedResult);
    }

    @Test
    @DisplayName("Must parse return empty array when expression is empty")
    public void must_ReturnEmptyArray_when_ExpressionIsEmpty() throws InvalidExpressionException {
        String expression =  "";
        String[] expectedResult = new String[] {};
        String[] elements = calculatorSimpleClient.parseExpression(expression);
        assertArrayEquals(elements, expectedResult);
    }

    @Test
    @DisplayName("Must parse return empty array when expression has only spaces")
    public void must_ReturnEmptyArray_when_ExpressionWithSpaces() throws InvalidExpressionException {
        String expression =  "      ";
        String[] expectedResult = new String[] {};
        String[] elements = calculatorSimpleClient.parseExpression(expression);
        assertArrayEquals(elements, expectedResult);
    }

    @Test
    @DisplayName("Must raise InvalidExpressionException when invalid expression is provided")
    public void must_RaiseInvalidExpressionException_when_InvalidExpressionProvided() {
        String[] expressions =  new String[] {"5,5", "2342wer", "abc", "5l5"};
        for (String expression : expressions) {
            assertThrows(InvalidExpressionException.class,
                    () -> calculatorSimpleClient.parseExpression(expression));
        }
    }

    @Test
    @DisplayName("Must add successfully")
    public void must_CalculateAdditionSuccessfully() {
        String[] elements = new String[] {"5", "+", "5"};
        Double result = calculatorSimpleClient.calculate(elements);
        assertEquals(result, 10.0);
    }

    @Test
    @DisplayName("Must subtract successfully")
    public void must_CalculateSubtractionSuccessfully() {
        String[] elements = new String[] {"5", "-", "5"};
        Double result = calculatorSimpleClient.calculate(elements);
        assertEquals(result, 0);
    }

    @Test
    @DisplayName("Must division successfully")
    public void must_CalculateDivisionSuccessfully() {
        String[] elements = new String[] {"5", "/", "5"};
        Double result = calculatorSimpleClient.calculate(elements);
        assertEquals(result, 1);
    }

    @Test
    @DisplayName("Must multiplication successfully")
    public void must_CalculateMultiplicationSuccessfully() {
        String[] elements = new String[] {"5", "*", "5"};
        Double result = calculatorSimpleClient.calculate(elements);
        assertEquals(result, 25);
    }

    @Test
    @DisplayName("Must raise ArithmeticException when a number is divided by zero")
    public void must_RaiseArithmeticException_when_DividedByZero() {
        String[] elements = new String[] {"5", "/", "0"};
        assertThrows(ArithmeticException.class,
                () -> calculatorSimpleClient.calculate(elements));
    }




} 
