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
public class SortTest<T> {
    private static final Comparator<Point> POINT_COMPARATOR_X = new Comparator<Point>() {
        public int compare(final Point p1, final Point p2) {
            return Integer.compare(p1.getX(), p2.getX());
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
    private static final Object[][] TEST_DATA = {
            {new InsertionSort<Point>(), POINT_COMPARATOR_X, randomPointsArray()},
            {new InsertionSort<Point>(), POINT_COMPARATOR_X, new Point []{}},
            {new InsertionSort<Point>(), POINT_COMPARATOR_X, new Point []{new Point(1,2),new Point(3,4), new Point(5,6)}},
            {new InsertionSort<Point>(), POINT_COMPARATOR_Y, randomPointsArray()},
            {new InsertionSort<Point>(), POINT_COMPARATOR_Y, new Point[]{new Point(1,2),new Point(0,1), new Point()}},

            {new InsertionSort<Double>(), DOUBLE_COMPARATOR1, new Double[]{1.234, 3.234, 1.353, 4.2421, 2.123, 5.12}},
            {new InsertionSort<Double>(), DOUBLE_COMPARATOR1, randomDoubleArray()},
            {new InsertionSort<Double>(), DOUBLE_COMPARATOR2, randomDoubleArray()},
            {new InsertionSort<Double>(), DOUBLE_COMPARATOR2, new Double[]{}},
            {new InsertionSort<Double>(), DOUBLE_COMPARATOR2, new Double[]{3.21,2.12, 2.01,1.434, 0.12424}}
    };
    private InsertionSort<T> sort;
    private T[] input;
    private Comparator<T> comparator;

    public SortTest(InsertionSort<T> sort, Comparator<T> comparator, T[] input) {
        this.sort = sort;
        this.input = input;
        this.comparator = comparator;
    }

    private static Point[] randomPointsArray() {
        Random rand = new Random();
        Point[] points = new Point[Math.abs(rand.nextInt()) % 20];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(rand.nextInt(), rand.nextInt());
        }
        return points;
    }

    private static Double[] randomDoubleArray() {
        Random rand = new Random();
        Double[] nums = new Double[Math.abs(rand.nextInt()) % 20];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = rand.nextDouble();
        }
        return nums;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(TEST_DATA);
    }

    private static <T> boolean testOrder(T[] array, Comparator<T> c) {
        for (int i = 0; i < array.length - 1; i++) {
            if (c.compare(array[i], array[i + 1]) >= 0)
                return false;
        }
        return true;
    }

    private static <T> boolean hasEachElementOf(T[] input, T[] result) {
        for (T element : input) {
            int c = 0;
            for (int j = 0; j < result.length; j++) {
                if (element.equals(result[j]))
                    c++;
                if (element.equals(input[j]))
                    c--;
            }
            if (c != 0)
                return false;
        }
        return true;
    }

    @Test
    public void test() {
        T[] result = sort.sort(input, comparator);
        Assert.assertTrue("Array wasn't sorted", testOrder(result, comparator));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue("Sorted array have different elements", hasEachElementOf(input, result));
    }
}