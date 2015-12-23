package ru.spbstu.appmath.bazaliy;

import ru.spbstu.appmath.bazaliy.exceptions.rangecalcexceptions.FormatException;
import ru.spbstu.appmath.bazaliy.exceptions.rangecalcexceptions.RangeCalcException;

import java.io.IOException;

public class RangeCalc {
    public static void main(String[] args) {
        try {
            if (args.length != 3)
                throw new FormatException();
            else {
                final FileCalc calculator = new FileCalc(args[0], args[1], new Limits(args[2]));
                calculator.Execute();
            }
        } catch (RangeCalcException | IOException e) {
            System.out.print(e.getMessage());
        }
    }


}
