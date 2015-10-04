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

@RunWith(Parameterized.class)
public class SortTest {
    private static final Sort SORT = new Sort();
    private static Object[][] TEST_DATA = {
            {SORT, new Double[]{1.0, 2.0, 3.0}},
            {SORT, new Double[]{3.0, 2.0, 1.0}},
            {SORT, new Double[]{1., 1., 1.}},
            {SORT, new Double[]{-100., 34., 43., -12052.5}},

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
    private static final Comparator<Point> POINT_COMPARATOR = new Comparator<Point>() {
        public int compare(final Point p1, final Point p2) {
            Integer temp = p1.getX();
            return temp.compareTo(p2.getX());
        }

    };

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {

        return Arrays.asList(TEST_DATA);
    }

    private Sort<Double> sort;
    private Double[] input;

    public SortTest(Sort<Double> sort, Double[] input) {
        this.sort = sort;
        this.input = input;
    }

    @Test
    public void test() {
        Double[] result = sort.sort(input, DOUBLE_COMPARATOR1);
        Assert.assertTrue(testAscendingOrder(result));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result));

        result = sort.sort(input, DOUBLE_COMPARATOR2);
        Assert.assertTrue(testDescendingOrder(result));
        Assert.assertEquals("Result array length should be equal to original", input.length, result.length);
        Assert.assertTrue(hasEachElementOf(input, result));

    }

    private static boolean testAscendingOrder(Double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1])
                return false;
        }
        return true;
    }

    private static boolean testDescendingOrder(Double[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1])
                return false;
        }
        return true;
    }

    private static boolean hasEachElementOf(Double[] input, Double[] result) {
        for (double element : input) {
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