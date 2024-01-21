package sn.esmt.calculator;

import java.util.HashSet;
import java.util.Set;

public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double substract(double a, double b) {
        return a - b;
    }

    public double divsion(double a, double b) {
        try {
            if (b != 0)
                return a / b;
        } catch(IllegalArgumentException e) {
            System.err.println("Error : " + e.getMessage());
        }

        return 0;
    }

    public void longCalculation() {
        try {
            // Attendre 2 secondes
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Error : " + e.getClass().getSimpleName() + " : " + e.getMessage());
        }
    }

    public Set<Integer> digitsSet(int number) {
        Set<Integer> integers = new HashSet<>();
        String numberString = String.valueOf(number);

        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) != '-')
                integers.add(Integer.parseInt(numberString, i, i + 1, 10));
        }

        return integers;
    }
}
