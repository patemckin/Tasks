package ru.spbstu.appmath.bazaliyv;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by admin on 12/12/15.
 */

class multiplyVectors implements Callable<Integer>
{
    public multiplyVectors(final int a[],final int b[]) throws Exception{
        if (a.length != b.length)
            throw new Exception("Different dimensions");
        this.a = a;
        this.b = b;
    }
    private final int[] a;
    private final int[] b;

    public Integer call() throws Exception {
        Integer result = 0;
        for (int i = 0; i < a.length; i++)
            result+= a[i]*b[i];
        return result;
    }
}

class Result {
    public Result(Future<Integer> value , int x, int y) {
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

    public Integer getValue() throws Exception{
        return value.get();
    }

    private int x;
    private int y;
    private Future<Integer> value;
}

public class MatrixCalc {
    public static void main(String[] args) {
        int Matrix1[][] = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int Matrix2[][] = new int[][]{{3,4,5},{6,7,8},{9,10,11}};
        int MatrixR[][] = new int[3][3];

        Set<Result> set = new HashSet<Result>();
        ExecutorService service = Executors.newFixedThreadPool(3);
        for(int i = 0; i < Matrix1.length; i++)
            for(int j = 0; j < Matrix1[0].length; ++j) {
                try {
                    Future<Integer> future = service.submit(new multiplyVectors(Matrix1[i], Matrix2[j]));
                    set.add(new Result(future,i,j));
                }catch(Exception ignored)
                {}
            }
        try {
            for (Result r : set) {

                MatrixR[r.getX()][r.getY()] = r.getValue();
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        for (int[] i:MatrixR) {
            for (int j : i)
                System.out.print(j + " ");
            System.out.println();
        }
    }

}
