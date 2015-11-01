package ru.spbstu.appmath.bazaliy;

import java.util.Arrays;
import java.util.Comparator;

import static java.lang.System.out;


public class InsertionSort<T> implements Sort<T> {

    private static double[] parseString(String[] input) {
        double result[] = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            result[i] = Double.parseDouble(input[i]);
        }
        return result;
    }

    public T[] sort(T[] arr, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(arr, arr.length);
        for (int j = 1; j < result.length; ++j) {
            T key = result[j];
            int i = j - 1;
            while (i >= 0 && (comparator.compare(result[i], key) >= 0))
                result[i + 1] = result[i--];
            result[i + 1] = key;
        }
        return result;
    }

    public static void printArray(double arr[]) {
        for (int i = 0; i < arr.length; ++i)
            out.printf("%g ", arr[i]);
        out.println();
    }
}
