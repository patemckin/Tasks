package ru.spbstu.appmath.bazaliy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.spbstu.appmath.bazaliy.exceptions.calcexceptions.CalculatorException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

@RunWith(Parameterized.class)
public class CalcTest {
    private static final Double epsilon = Math.pow(10, -15);
    private String expression;
    private Double variable;
    private Double result;
    private String exception;

    private static ArrayList<Object[]> initializeTestData() throws IllegalArgumentException {
        try (Scanner f = new Scanner(CalcTest.class.getClassLoader().getResourceAsStream("tests.txt"))) {
            ArrayList<Object[]> tests = new ArrayList<Object[]>();
            while (f.hasNextLine()) {
                String line = f.nextLine();
                String[] data = line.split("@");
                tests.add(new Object[]{data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[3]});

            }
            f.close();
            return tests;
        }
    }

    public CalcTest(String expression, Double variable, Double result, String exceptionMessage) {
        this.expression = expression;
        this.variable = variable;
        this.result = result;
        this.exception = exceptionMessage;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() throws IllegalArgumentException{
        return initializeTestData();
    }

    @Test
    public void test() {
        try {
            ExpTree expTree = new ExpBuilder(expression).build();
            double countedValue = expTree.execute(variable);
            ;
            Assert.assertTrue("Wrong answer", Math.abs(countedValue - result) < epsilon);
        } catch (CalculatorException e) {
            Assert.assertTrue("Different exception", exception.equals(e.getMessage()));
        }
    }

}
