import static java.lang.System.out;

public class Sort {
    public static void main(String[] args) {
        double a[] = parseString(args);
        sort(a);
        printArray(a);
    }

    private static double[] parseString(String[] input) {
        double result[] = new double[input.length];
        for (int i = 0; i < input.length; i++)
            result[i] = Double.parseDouble(input[i]);
        return result;
    }

    public static boolean sort(double arr[]) {
        for (int j = 1; j < arr.length; ++j) {
            double key = arr[j];
            int i = j - 1;
            while (i >= 0 && arr[i] > key)
                arr[i + 1] = arr[i--];
            arr[i + 1] = key;
        }
        return true;
    }

    public static void printArray(double arr[]) {
        for (int i = 0; i < arr.length; ++i)
            out.printf("%g ", arr[i]);
        out.println();
    }
}