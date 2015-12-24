package ru.spbstu.appmath.bazaliyv;

import ru.spbstu.appmath.bazaliyv.exceptions.DimensionsException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by admin on 24/12/15.
 */
public class IOMatrix {
    static Matrix getMatrixFromFile(final String fileName) throws IOException, DimensionsException {
        Scanner input = new Scanner(IOMatrix.class.getClassLoader().getResourceAsStream(fileName));
        int rows = 0;
        int columns = 0;
        try {
            while (input.hasNextLine()) {
                String[] values = input.nextLine().split(" ");
                if (rows == 0)
                    columns = values.length;
                if (rows > 0 && columns != values.length)
                    throw new DimensionsException();
                rows++;
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            input.close();
            throw new IOException(e);
        }

        input.close();

        double matrix[][] = new double[rows][columns];
        input = new Scanner(IOMatrix.class.getClassLoader().getResourceAsStream(fileName));
        try {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = input.nextDouble();
                }
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            input.close();
            throw new IOException(e);
        }
        input.close();

        return new Matrix(matrix);
    }

    public static void printInFile(final Matrix matrix, final String outputFile) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputFile);
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++)
                printWriter.print(matrix.data[i][j] + " ");
            printWriter.println();
        }
        printWriter.close();
    }

    public static void print(Matrix matrix) {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++)
                System.out.print(matrix.data[i][j] + " ");
            System.out.println();
        }
    }

}
