package com.vector.client;

import com.vector.calculator.ICalculator;
import com.vector.client.exception.InvalidExpressionException;

import java.util.ArrayList;

public class CalculatorSimpleClient {

    private final ICalculator calculator;

    private final char ADDITION = '+';
    private final char SUBTRACT = '-';
    private final char MULTIPLY = '*';
    private final char DIVISION = '/';

    public CalculatorSimpleClient(ICalculator calculator) {
        this.calculator = calculator;
    }

    public Double calculate(String[] elements) {
        char lastOperator = ' ';
        for (String element : elements) {
            try {
                double value = Double.parseDouble(element);
                switch (lastOperator) {
                    case ADDITION -> calculator.add(value);
                    case SUBTRACT -> calculator.subtract(value);
                    case DIVISION -> calculator.divide(value);
                    case MULTIPLY -> calculator.multiply(value);
                    default -> calculator.setValue(value);
                }
            } catch (NumberFormatException e) {
                lastOperator = element.charAt(0);
            } catch (Exception e) {
                calculator.setValue(0);
                throw e;
            }
        }
        return calculator.getResult();
    }

    public String[] parseExpression(String expression) throws InvalidExpressionException {

        ArrayList<String> elements = new ArrayList<>();

        expression = sanitizeExpression(expression);
        if (expression.isEmpty()) {
            return new String[0];
        }
        checkExpression(expression);

        StringBuilder element = new StringBuilder();
        for (char c : expression.toCharArray()) {
            switch (c) {
                case ADDITION, SUBTRACT, DIVISION, MULTIPLY -> {
                    elements.add(element.toString());
                    element = new StringBuilder();
                    elements.add(String.valueOf(c));
                }
                default -> element.append(c);
            }
        }
        elements.add(element.toString());

        return elements.toArray(String[]::new);
    }

    private String sanitizeExpression(String expression){
        return expression.replaceAll(" ", "");
    }

    private void checkExpression(String expression) throws InvalidExpressionException {
        String regex = "[-/*+.[0-9]]+";
        if (!expression.matches(regex)){
            throw new InvalidExpressionException();
        }
    }

}
