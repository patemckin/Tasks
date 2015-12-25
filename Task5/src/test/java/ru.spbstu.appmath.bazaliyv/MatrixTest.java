package ru.spbstu.appmath.bazaliyv;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.spbstu.appmath.bazaliyv.exceptions.MatrixCalcException;

import java.util.Arrays;
import java.util.Collection;

import static ru.spbstu.appmath.bazaliyv.IOMatrix.getMatrixFromFile;

@RunWith(Parameterized.class)
public class MatrixTest {
    private final Matrix A;
    private final Matrix B;
    private final Matrix R;
    private final Integer threadsNum;


    private final static Object[][] SIMPLE_TESTS = new Object[][]{
            {new double[][]{{0.0}}, new double[][]{{0.0}}, new double[][]{{0.0}}, 1},
            {new double[][]{{1.0, 2.0}, {3.0, 4.0}}, new double[][]{{5.0, 6.0}, {7.0, 8.0}}, new double[][]{{19., 22.}, {43., 50.}}, 1},
            {new double[][]{{1.0, 2.0}, {3.0, 4.0}}, new double[][]{{5.0, 6.0}, {7.0, 8.0}}, new double[][]{{19., 22.}, {43., 50.}}, 2}
    };

    public MatrixTest(double[][] A, double[][] B, double[][] R, Integer threadsNum) {
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
            Matrix result = new MatrixMultiplication(A, B, threadsNum).multiply();
            Assert.assertEquals(R, result);
        } catch (MatrixCalcException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFromFile() {
        try {
            Matrix A = getMatrixFromFile("matrix_generator/A1.txt");
            Matrix B = getMatrixFromFile("matrix_generator/B1.txt");
            Matrix R = getMatrixFromFile("matrix_generator/C1.txt");
            Matrix result = new MatrixMultiplication(A, B, 50).multiply();
            Assert.assertEquals(R, result);
        } catch (MatrixCalcException e) {
            e.printStackTrace();
        }
    }

}