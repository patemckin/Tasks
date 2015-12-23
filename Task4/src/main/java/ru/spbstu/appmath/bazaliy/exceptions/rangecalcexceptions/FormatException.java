package ru.spbstu.appmath.bazaliy.exceptions.rangecalcexceptions;

/**
 * Created by admin on 24/12/15.
 */
public class FormatException extends RangeCalcException{
    public FormatException() {
        super("Wrong format");
    }
    public FormatException(final String message) {
        super("Wrong format:" + message);
    }
}
