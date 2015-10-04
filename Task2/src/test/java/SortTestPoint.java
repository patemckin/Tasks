/**
 * Created by admin on 03/10/15.
 */

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.spbstu.appmath.bazaliy.Point;
import ru.spbstu.appmath.bazaliy.Sort;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

@RunWith(Parameterized.class)
public class SortTestPoint {
    private static final Sort SORT = new Sort();

    private static final Comparator<Point> POINT_COMPARATOR_X = new Comparator<Point>() {
        public int compare(final Point p1, final Point p2) {
            Integer temp = p1.getX();
            return temp.compareTo(p2.getX());
        }
    };
    private static final Comparator<Point> POINT_COMPARATOR_Y = new Comparator<Point>() {
        public int compare(final Point p1, final Point p2) {
            Integer temp = p1.getY();
            return temp.compareTo(p2.getY());
        }
    };


    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        Random rand = new Random();
        Point[][] points = new Point[5][5];
        for (int i = 0; i < 5; ++i) {
            points[1][i] = new Point(i, 0);
            points[2][i] = new Point(0, i);
            points[3][i] = new Point(5 - i, 0);
            points[4][i] = new Point(i, 5 - i);
            points[5][i] = new Point(rand.nextInt(), rand.nextInt());
        }
        Object[][] TEST_DATA = {
                {SORT, points[1]},
                {SORT, points[2]},
                {SORT, points[3]},
                {SORT, points[4]}
        };
        return Arrays.asList(TEST_DATA);
    }

    private Sort<Point> sort;
    private Point[] input;

    public SortTestPoint(Sort<Point> sort, Point[] input) {
        this.sort = sort;
        this.input = input;
    }

    @Test
    public void test() {
        Point[] result = sort.sort(input, POINT_COMPARATOR_X);
        Assert.assertTrue(testAscendingOrderX(result));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result));

        result = sort.sort(input, POINT_COMPARATOR_Y);
        Assert.assertTrue(testAscendingOrderY(result));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result));

    }

    private static boolean testAscendingOrderX(Point[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getX() > array[i + 1].getX())
                return false;
        }
        return true;
    }

    private static boolean testAscendingOrderY(Point[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].getY() < array[i + 1].getY())
                return false;
        }
        return true;
    }

    private static boolean hasEachElementOf(Point[] input, Point[] result) {
        for (Point element : input) {
            for (int j = 0; j < result.length; j++) {
                if (result[j].Equal(element))
                    break;
                if (j == result.length - 1)
                    return false;
            }
        }
        return true;
    }
}