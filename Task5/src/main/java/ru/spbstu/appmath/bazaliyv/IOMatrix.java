package ru.spbstu.appmath.bazaliyv;

import ru.spbstu.appmath.bazaliyv.exceptions.DimensionsException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by admin on 24/12/15.
 */
public class IOMatrix {
    static Matrix getMatrixFromFile(final String fileName) throws DimensionsException {
        int rows = 0;
        int columns = 0;
        try (Scanner input = new Scanner(IOMatrix.class.getClassLoader().getResourceAsStream(fileName))) {
            while (input.hasNextLine()) {
                String[] values = input.nextLine().split(" ");
                if (rows == 0)
                    columns = values.length;
                if (rows > 0 && columns != values.length)
                    throw new DimensionsException();
                rows++;
            }
            input.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


        double matrix[][] = new double[rows][columns];
        try (Scanner input = new Scanner(IOMatrix.class.getClassLoader().getResourceAsStream(fileName))) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = input.nextDouble();
                }
            }
            input.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return new Matrix(matrix);
    }

    public static void printInFile(final Matrix matrix, final String outputFile) {
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++)
                    printWriter.print(matrix.data[i][j] + " ");
                printWriter.println();
            }
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void print(Matrix matrix) {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++)
                System.out.print(matrix.data[i][j] + " ");
            System.out.println();
        }
    }

}
