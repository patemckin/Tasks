package ru.spbstu.appmath.bazaliy;

import java.util.Comparator;


public interface Sortings<T> {
    T[] sort(T[] array, Comparator<T> comparator);
}
