package ru.spbstu.appmath.bazaliyv;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by admin on 12/12/15.
 */
public class Matrix {

    public Matrix (int rows, int cols){
        this.matrix = new Double[rows][cols];
    }
    public Matrix(String fileName) throws IOException{
        //System.out.println(this.getClass().getResource(fileName));
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        int rows = 0;
        int columns = 0;

        while(input.hasNextLine()) {
            String[] values = input.nextLine().split(" ");
            if (rows == 0)
                columns = values.length;
            if (rows > 0 && columns != values.length)
                throw new IOException("File doesn't contain valid matrix");
            rows++;
        }

        input.close();

        this.matrix = new Double[rows][columns];
        //input = new Scanner(new File(this.getClass().getResource(fileName).getFile()));
        input = new Scanner(file.getAbsoluteFile());
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                    this.matrix[i][j] = input.nextDouble();
            }
        }
        input.close();

    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(this.matrix[i][j] + " ");
            System.out.println();
        }
    }
    public void printInFile(String outputFile) throws IOException{
        PrintWriter printWriter = new PrintWriter(outputFile);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                printWriter.print(this.matrix[i][j] + " ");
            printWriter.println();
        }
        printWriter.close();
    }


    public int getColumns(){
        return matrix[0].length;
    }
    public int getRows(){
        return matrix.length;
    }
    public Double[][] matrix;
}
