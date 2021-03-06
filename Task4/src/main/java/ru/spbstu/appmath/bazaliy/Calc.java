package ru.spbstu.appmath.bazaliy;

import ru.spbstu.appmath.bazaliy.exceptions.calcexceptions.CalculatorException;
import ru.spbstu.appmath.bazaliy.exceptions.calcexceptions.SyntaxException;

public class Calc {
    public static void main(String[] args) {
        try {
            final String expression = args[0];
            if (args.length > 2 || args.length == 0)
                throw new SyntaxException("Too much arguments");

            final ExpBuilder exp = new ExpBuilder(expression);
            final ExpTree expTree = exp.build();
            final double result;

            if (args.length == 2) {
                Double value = Double.parseDouble(args[1]);
                if (!expTree.hasVar())
                    throw new SyntaxException("Expression hasn't varible");

                result = expTree.execute(value);
            } else {
                if (expTree.hasVar())
                    throw new SyntaxException("Variable hasn't been entered");
                result = expTree.execute(0);
            }
            System.out.print(result);
        } catch (CalculatorException e) {
            System.err.print(e.getMessage());
        }
    }
}
