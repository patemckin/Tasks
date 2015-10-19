package ru.spbstu.appmath.bazaliy;

public class main {
    public static void main(String[] args) throws Exception {
        String expression = args[0];
        Double value = 0.0;
        if (args.length > 1) {
            try {
                value = Double.parseDouble(args[1]);
            } catch (Exception e) {
            }
        }
        ExpBuilder exp = new ExpBuilder(expression);
        ExpTree expTree = exp.getExp();
        Double result = (Double) expTree.execute(value);
        System.out.print(result);
    }
}
