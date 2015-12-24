package ru.spbstu.appmath.bazaliyv;

/**
 * Created by admin on 12/12/15.
 */
public class Matrix {
    public double[][] data;

    public Matrix(final int rows, final int cols) {
        this.data = new double[rows][cols];
    }

    public Matrix(final double[][] data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix))
            return false;
        Matrix M = (Matrix) o;
        if (getColumns() != M.getColumns() || getRows() != M.getRows())
            return false;
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (Math.abs(data[i][j] - M.data[i][j]) > 1e-5)
                    return false;
            }
        }
        return true;
    }

    public int getColumns() {
        if (getRows() == 0 || data[0] == null)
            return 0;
        return data[0].length;
    }

    public int getRows() {
        if (data == null)
            return 0;
        return data.length;
    }
}
