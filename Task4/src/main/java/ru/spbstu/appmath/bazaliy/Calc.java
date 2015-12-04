package ru.spbstu.appmath.bazaliy;

public class Calc {
    public static void main(String[] args){
        try {
            FileCalc calculator = new FileCalc("input.txt", "output.txt", 1, 10);
            calculator.Execute();
        } catch(Exception e)
        {
            System.out.print(e.getMessage());
        }

    }
/*        try {
            String expression = args[0];
            Double value = 0.0;
            boolean isVariableInited = false;
            if (args.length > 2 || args.length == 0)
                throw new VariableException("Too much arguments");
            if (args.length == 2) {
                value = Double.parseDouble(args[1]);
                isVariableInited = true;
            }
            ExpTree expTree = new ExpBuilder(expression).build();
            double result;
            if (isVariableInited)
                 result = expTree.execute(value);
            else
                result = expTree.execute();
            System.out.print(result);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }*/
}
