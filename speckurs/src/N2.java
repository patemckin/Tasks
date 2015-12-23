import java.util.Scanner;

public class N2 {

    public static void main(String[] args) {
        Scanner rd = new Scanner(System.in);
        double x = rd.nextDouble();
        double k = 0;
        double a = 0;
        double q = 0.0000001;
        double xk = 2 + q;
        while (Math.abs(xk - a) > q) {
            double n = Math.sin(1 / x);
            xk = x + 2 * (-x + 2 - n);
            a = x;
            x = xk;
            k = k + 1;
        }
        System.out.println(xk);
        System.out.println(k);

    }
}
