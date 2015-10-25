package ru.spbstu.appmath.bazaliy;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

@RunWith(Parameterized.class)
public class SortTest {
    private static final Comparator<Point> POINT_COMPARATOR_X = new Comparator<Point>() {
        public int compare(final Point p1, final Point p2) {
            return Integer.compare(p1.getX(),p2.getX());
        }
    };
    private static final Comparator<Point> POINT_COMPARATOR_Y = new Comparator<Point>() {
        public int compare(final Point p1, final Point p2) {
            return Integer.compare(p1.getY(), p2.getY());
        }
    };
    private static final Comparator<Double> DOUBLE_COMPARATOR1 = new Comparator<Double>() {
        public int compare(final Double d1, final Double d2) {
            return d1.compareTo(d2);
        }
    };

    private static final Comparator<Double> DOUBLE_COMPARATOR2 = new Comparator<Double>() {
        public int compare(final Double o1, final Double o2) {
            return o2.compareTo(o1);
        }
    };

    private static Point[] randomPointsArray(){
        Random rand = new Random();
        Point[] Points = new Point[Math.abs(rand.nextInt())%20];
        for (int i = 0; i < Points.length;i++){
            Points[i] = new Point(rand.nextInt(),rand.nextInt());
        }
        return Points;
    }
    private static Double[] randomDoubleArray(){
        Random rand = new Random();
        Double[] Nums = new Double[Math.abs(rand.nextInt())%20];
        for (int i = 0; i < Nums.length;i++){
            Nums[i] = rand.nextDouble();
        }
        return Nums;
    }

    private static final Object[][] TEST_DATA = {
            {new InsertionSort<Point>(), POINT_COMPARATOR_X, randomPointsArray()},
            {new InsertionSort<Point>(), POINT_COMPARATOR_Y, randomPointsArray()},
            {new InsertionSort<Double>(), DOUBLE_COMPARATOR1, new Double[]{1.234,3.234,1.353,4.2421,2.123,5.12}},
            {new InsertionSort<Double>(), DOUBLE_COMPARATOR2, randomDoubleArray()}
    };

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(TEST_DATA);
    }

    private InsertionSort sort;
    private Object[] input;
    private Comparator comparator;

    public SortTest(InsertionSort sort, Comparator comparator, Point[] input) {
        this.sort = sort;
        this.input = input;
        this.comparator = comparator;
    }

    @Test
    public void test() {
        Object[] result = sort.sort(input,comparator);
        Assert.assertTrue("Array wasn't sorted",testOrder(result, comparator));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue("Sorted array have different elements",hasEachElementOf(input, result));

    }


    private static <T> boolean testOrder(T[] array, Comparator<T> c)
    {
        for (int i = 0; i < array.length - 1; i++) {
            if (c.compare(array[i],array[i+1]) >= 0)
                return false;
        }
        return true;
    }


    private static <T> boolean hasEachElementOf(T[] input, T[] result) {
        for (T element : input) {
            for (int j = 0; j < result.length; j++) {
                if (result[j] == element)
                    break;
                if (j == result.length - 1)
                    return false;
            }
        }
        return true;
    }
}