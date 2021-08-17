package com.vector.calculator;

public interface ICalculator {
    void add( double value);
    void divide(double value);
    void subtract(double value);
    void multiply( double value);
    double getResult();
    void clear();
    void setValue(double value);

}
