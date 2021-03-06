package ru.spbstu.appmath.bazaliy;

import ru.spbstu.appmath.bazaliy.MyExceptions.*;

public class Calc {
    public static void main(String[] args){
        try {
            String expression = args[0];
            Double value = 0.0;
            boolean isVariableInited = false;
            if (args.length > 2 || args.length == 0)
                throw new VariableException("Too much arguments");
            if (args.length == 2) {
                value = Double.parseDouble(args[1]);
                isVariableInited = true;
            }
            ExpBuilder exp = new ExpBuilder(expression);
            ExpTree expTree = exp.build();
            double result;
            if (isVariableInited)
                 result = expTree.execute(value);
            else
                result = expTree.execute();
            System.out.print(result);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
