package ru.spbstu.appmath.bazaliyv;

import ru.spbstu.appmath.bazaliyv.exceptions.DimensionsException;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by admin on 13/12/15.
 */
class MatrixMultiplication {

    private final Matrix matrix1;
    private final Matrix matrix2;
    private final int multiplyLength;
    private final int threadsNumber;

    private class Result {
        public Result(final Future<Double> value, final int x, final int y) {
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

        public Double getValue() throws Exception {
            return value.get();
        }

        private final int x;
        private final int y;
        private final Future<Double> value;
    }

    class MultiplyVectorsTask implements Callable<Double> {
        public MultiplyVectorsTask(final int nRow, final int nCol) {
            this.nCol = nCol;
            this.nRow = nRow;
        }

        private final int nRow;
        private final int nCol;

        public Double call() throws Exception {
            Double result = 0.0;
            for (int i = 0; i < multiplyLength; i++)
                result += matrix1.data[nRow][i] * matrix2.data[i][nCol];
            return result;
        }
    }

    public MatrixMultiplication(final Matrix matrix1, final Matrix matrix2, final int threadsNumber) throws DimensionsException {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        if (matrix1.getColumns() != matrix2.getRows())
            throw new DimensionsException();
        this.multiplyLength = matrix1.getColumns();
        this.threadsNumber = threadsNumber;
    }

    public Matrix multiply() {
        Matrix result = new Matrix(matrix1.getRows(), matrix2.getColumns());
        Set<Result> set = new HashSet<Result>();
        ExecutorService service = Executors.newFixedThreadPool(threadsNumber);
        for (int i = 0; i < matrix1.getRows(); i++)
            for (int j = 0; j < matrix2.getColumns(); ++j) {
                try {
                    Future<Double> future = service.submit(new MultiplyVectorsTask(i, j));
                    set.add(new Result(future, i, j));
                } catch (Exception ignored) {
                }
            }
        try {
            for (Result r : set) {
                result.data[r.getX()][r.getY()] = r.getValue();
            }
        } catch (Exception ignored) {
        }
        service.shutdown();
        return result;
    }
}
