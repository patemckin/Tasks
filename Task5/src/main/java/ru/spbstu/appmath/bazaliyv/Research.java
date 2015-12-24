package ru.spbstu.appmath.bazaliyv;

import ru.spbstu.appmath.bazaliyv.exceptions.DimensionsException;
import ru.spbstu.appmath.bazaliyv.exceptions.MatrixCalcException;

import java.io.IOException;
import java.io.PrintWriter;

import static ru.spbstu.appmath.bazaliyv.IOMatrix.getMatrixFromFile;

/**
 * Created by admin on 18/12/15.
 */
public class Research {
    public static void main(String[] args) {
        try {
            doResearch("matrix_generator/A1.txt", "matrix_generator/B1.txt", "small.txt");
            doResearch("matrix_generator/A2.txt", "matrix_generator/B2.txt", "medium.txt");
            doResearch("matrix_generator/A3.txt", "matrix_generator/B3.txt", "huge.txt");
        } catch (MatrixCalcException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void doResearch(final String firstMatrix, final String secondMatrix, final String report) throws DimensionsException, IOException {
        int threads = 1;

        PrintWriter printWriter = new PrintWriter("research/" + report);
        Matrix A = getMatrixFromFile(firstMatrix);
        Matrix B = getMatrixFromFile(secondMatrix);
        for (threads = 1; threads <= 1000; threads *= 10) {
            MatrixMultiplication multiplyMatrix = new MatrixMultiplication(A, B, threads);
            long startTime = System.currentTimeMillis();
            multiplyMatrix.multiply();
            long totalTime = System.currentTimeMillis() - startTime;
            printWriter.println(threads + "\t" + totalTime);
        }
        printWriter.close();
    }
}
