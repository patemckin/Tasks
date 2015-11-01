package ru.spbstu.appmath.bazaliy;

import java.util.Arrays;
import java.util.Comparator;


public class InsertionSort<T> implements Sort<T> {
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
}
