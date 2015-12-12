package ru.spbstu.appmath.bazaliyv;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by admin on 13/12/15.
 */
 class MultiplyMatrix {

    private class Result {
        public Result(Future<Double> value , int x, int y) {
            this.value = value;
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Double getValue() throws Exception{
            return value.get();
        }

        private int x;
        private int y;
        private Future<Double> value;
    }

    class MultiplyVectorsTask implements Callable<Double>
    {
        public MultiplyVectorsTask(int nRow, int nCol){
            this.nCol = nCol;
            this.nRow = nRow;
        }
        private final int nRow;
        private final int nCol;

        public Double call() throws Exception {
            Double result = 0.0;
            for (int i = 0; i < multiplyLength; i++)
                result+= matrix1.matrix[nRow][i]*matrix2.matrix[i][nCol];
            return result;
        }
    }
    public MultiplyMatrix(Matrix matrix1, Matrix matrix2, int threadsNumber) throws IOException {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        if ( matrix1.getColumns() != matrix2.getRows())
            throw new IOException("Different dimensions");
        this.multiplyLength = matrix1.getColumns();
        this.threadsNumber = threadsNumber;
    }
    public Matrix multiply() {
        Matrix result = new Matrix(matrix1.getRows(),matrix2.getColumns());
        Set<Result> set = new HashSet<Result>();
        ExecutorService service = Executors.newFixedThreadPool(threadsNumber);
        for(int i = 0; i < matrix1.getRows(); i++)
            for(int j = 0; j < matrix2.getColumns(); ++j) {
                try {
                    Future<Double> future = service.submit(new MultiplyVectorsTask(i,j));
                    set.add(new Result(future,i,j));
                }catch(Exception ignored) {
                }
            }
        try {
            for (Result r : set) {
                result.matrix[r.getX()][r.getY()] = r.getValue();
            }
        } catch (Exception ignored) {}
        service.shutdown();
        return result;
    }

    private Matrix matrix1;
    private Matrix matrix2;
    private int multiplyLength;
    private int threadsNumber;
}
