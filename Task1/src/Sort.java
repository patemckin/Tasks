/**
 * Created by admin on 17/09/15.

 */
import static java.lang.System.out;

import java.util.Arrays;
import java.util.Scanner;

public class First {
    public static void main(String[] args){
        out.println("Selection sort test");
        double a[] = readArr();
        //out.print("Basic array:");
        //printArray(a);
        sort(a);
        out.print("Sorted array: ");
        printArray(a);
    }
    public static double [] readArr() {

        Scanner s = new Scanner(System.in);
        out.print("Enter the number of elements in array: ");
        int count = s.nextInt();
        s.nextLine(); // throw away the newline.
        out.print("Your array: ");
        double [] numbers = new double[count];
        Scanner numScanner = new Scanner(s.nextLine());
        for (int i = 0; i < count; i++) {
            if (numScanner.hasNextDouble()) {
                numbers[i] = numScanner.nextDouble();
            } else {
                out.println("You didn't provide enough numbers");
                break;
            }
        }

        return numbers;
    }

    public static boolean  sort(double arr[]){
        double key;

        for (int j = 1; j < arr.length; ++j) {
            key = arr[j];
            int i = j - 1;
            while (i >= 0 && arr[i] > key)
                arr[i + 1] = arr[i--];
            arr[i+1] = key;
        }
        return true;
    }
    public static void    printArray(double arr[]) {
        for(int i = 0; i < arr.length;++i)
            out.printf("%g ",arr[i]);
        out.println();
    }

}
