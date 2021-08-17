package com.vector.client;

import com.vector.calculator.Calculator;
import com.vector.client.exception.InvalidExpressionException;

import java.util.Scanner;

public class CalculatorSimpleClientRunner {
    private static final char EXIT_CHAR = 'q';

    public static void main(String[] args) {
        CalculatorSimpleClient calculatorSimpleClient = new CalculatorSimpleClient(new Calculator());
        Scanner scanner = new Scanner(System.in);
        String inputExpression;
        do {
            System.out.print("Enter your expression (eg. 10.65+20/55-99). Type \"" + EXIT_CHAR + "\" to finish : ");
            inputExpression = scanner.next();

            if (!inputExpression.equals(String.valueOf(EXIT_CHAR))) {
                try {
                    String[] elements = calculatorSimpleClient.parseExpression(inputExpression);
                    Double result = calculatorSimpleClient.calculate(elements);
                    showResult(result);
                } catch (Exception e) {
                    if (e instanceof InvalidExpressionException) {
                        System.out.println("Error: Invalid Expression!");
                    } else if (e instanceof ArithmeticException) {
                        System.out.println("Error: Division by zero!");
                    } else {
                        System.out.println("Error: Sorry, something went wrong. Try again!");
                    }
                }
            }
        }
        while (!inputExpression.equals("q"));

        System.out.println("See you bye...");

    }

    private static void showResult(Double result) {
        System.out.printf("Result: %.2f%n", result);
    }
}
