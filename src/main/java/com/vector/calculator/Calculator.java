package com.vector.calculator;

public class Calculator implements ICalculator {

    Double result = (double) 0;

    @Override
    public void add(double value) {
        result += value;
    }

    @Override
    public void divide(double value) {
        result /= value;
        if (result.equals(Double.NaN) || result.equals(Double.POSITIVE_INFINITY) || result.equals(Double.NEGATIVE_INFINITY)) {
            throw new ArithmeticException();
        }
    }

    @Override
    public void subtract(double value) {
        result -= value;
    }

    @Override
    public void multiply(double value) {
        result *= value;
    }

    @Override
    public double getResult() {
        return result;
    }

    @Override
    public void clear() {
        result = (double) 0;
    }

    @Override
    public void setValue(double value) {
        result = value;
    }
}
