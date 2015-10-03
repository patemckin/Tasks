import java.util.Comparator;


public interface Sortings<T> {
    T[] sort(T[] array, Comparator<T> comparator);
}
