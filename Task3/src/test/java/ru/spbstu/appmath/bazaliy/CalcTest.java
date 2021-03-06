package ru.spbstu.appmath.bazaliy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

/**
 * Created by admin on 30/10/15.
 */
@RunWith(Parameterized.class)
public class CalcTest {
    /* = {
          /  {"1+2+3",0.0,6.0,""},
            {"1/x + 1 ",2.0, 1.5,""},
            {"-1 + 2 - x",1.0,0.0,""},
            {"x*x - x +1.23 ",2.0, 3.23,""},
            {"150*x - 2/x +3.14",2.0,302.14,""},
            {"Привет",2.0,302.14,"Wrong symbols in expression"},
            {"1 +1/0",0.0,0.0,"Division by zero"},
            {" 1+ (",0.0,0.0,"Wrong syntax"}
    };*/
    private static final Double epsilon = Math.pow(10, -15);
    private String expression;
    private Double variable;
    private Double result;
    private String exception;

    // @Rule
    // public ExpectedException thrown= ExpectedException.none();

    private static Object[][] initializeTestData() throws FileNotFoundException {
        Scanner f = new Scanner(new File("tests.txt").getAbsolutePath()).useDelimiter("\\s*,\\s*");
        Object[][] TEST_DATA = new Object[8][4];
        int i = 0;
        System.out.println("1");
        while (f.hasNextLine()) {
            System.out.println("2");
            TEST_DATA[i][0] = f.next();
            TEST_DATA[i][1] = f.nextDouble();
            TEST_DATA[i][2] = f.nextDouble();
            TEST_DATA[i][3] = f.next();
            System.out.print(TEST_DATA[i][1]);
            i++;
        }
        return TEST_DATA;
    }

    public CalcTest(String expression, Double variable, Double result, String exceptionMessage) {
        this.expression = expression;
        this.variable = variable;
        this.result = result;
        this.exception = exceptionMessage;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() throws Exception{
        Object[][] TEST_DATA= initializeTestData();
        return Arrays.asList(TEST_DATA);
    }

    @Test
    public void test() {
        try {
            ExpBuilder exp = new ExpBuilder(expression);
            ExpTree expTree = exp.getExp();
            Double countedValue = expTree.execute(variable);
            Assert.assertTrue("Wrong answer", Math.abs(countedValue - result) < epsilon);
            //Assert.check(Math.abs(countedValue - result) < epsilon, "Wrong answer");
        } catch (Exception e) {
            System.out.print(e.getMessage());
            Assert.assertSame("Different exception", e.getMessage(), exception);
        }
    }

}
