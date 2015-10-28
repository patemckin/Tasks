package ru.spbstu.appmath.bazaliy;

import java.util.Comparator;


public interface Sort<T> {
    T[] sort(final T[] array, Comparator<T> comparator);
}
