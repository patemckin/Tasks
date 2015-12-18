package ru.spbstu.appmath.bazaliyv;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MatrixTest {
    private final Matrix A;
    private final Matrix B;
    private final Matrix R;
    private final Integer threadsNum;


    private final static Object[][] SIMPLE_TESTS = new Object[][]{
            {new Double[][]{{0.0}}, new Double[][]{{0.0}}, new Double[][]{{0.0}}, 1},
            {new Double[][]{{1.0, 2.0}, {3.0, 4.0}}, new Double[][]{{5.0, 6.0}, {7.0, 8.0}}, new Double[][]{{19., 22.}, {43., 50.}}, 1},
            {new Double[][]{{1.0, 2.0}, {3.0, 4.0}}, new Double[][]{{5.0, 6.0}, {7.0, 8.0}}, new Double[][]{{19., 22.}, {43., 50.}}, 2}
    };

    public MatrixTest(Double[][] A, Double[][] B, Double[][] R, Integer threadsNum) {
        this.A = new Matrix(A);
        this.B = new Matrix(B);
        this.R = new Matrix(R);
        this.threadsNum = threadsNum;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() throws Exception {
        return Arrays.asList(SIMPLE_TESTS);
    }

    @Test
    public void testSimple() {
        try {
            Matrix result = new MultiplyMatrix(A, B, threadsNum).multiply();
            Assert.assertEquals(R, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFromFile() {
        try {
            Matrix A = new Matrix("matrix_generator/A1.txt");
            Matrix B = new Matrix("matrix_generator/B1.txt");
            Matrix R = new Matrix("matrix_generator/C1.txt");
            Matrix result = new MultiplyMatrix(A, B, 50).multiply();
            Assert.assertEquals(R, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}