package com.vector.calculator;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    private ICalculator operation;
    private final double valueInit = 100;
    private final double valueOperation = 100;


    @BeforeAll
    private void init() {
        operation = new Calculator();
    }

    @BeforeEach
    private void cleanUp() {
        operation.clear();
    }

    @Test
    @DisplayName("Must add value successfully")
    public void must_AddSuccessfully() {
        operation.setValue(valueInit);
        operation.add(valueOperation);
        assertEquals(valueInit + valueOperation, operation.getResult());
    }

    @Test
    @DisplayName("Must divide value successfully")
    public void must_DivideSuccessfully()  {
        operation.setValue(valueInit);
        operation.divide(valueOperation);
        assertEquals(valueInit / valueOperation, operation.getResult());
    }

    @Test
    @DisplayName("Must raise ArithmeticException for divisor equals zero")
    public void must_RaiseArithmeticException_when_DivisorIsZero() {
        assertThrows(ArithmeticException.class,
                () -> operation.divide(0));
    }

    @Test
    @DisplayName("Must subtract value successfully")
    public void must_SubtractSuccessfully() {
        operation.setValue(valueInit);
        operation.subtract(valueOperation);
        assertEquals(valueInit - valueOperation, operation.getResult());
    }

    @Test
    @DisplayName("Must multiply value successfully")
    public void must_MultiplySuccessfully() {
        operation.setValue(valueInit);
        operation.multiply(valueOperation);
        assertEquals(valueInit * valueOperation, operation.getResult());
    }

    @Test
    @DisplayName("Must clear value successfully")
    public void must_ClearTheCalculator() {
        operation.setValue(valueInit);
        operation.clear();
        assertEquals(0, operation.getResult());
    }
} 
