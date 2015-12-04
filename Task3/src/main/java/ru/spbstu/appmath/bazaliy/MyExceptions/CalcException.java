package ru.spbstu.appmath.bazaliy.MyExceptions;

/**
 * Created by admin on 04/12/15.
 */
public class CalcException extends Error {
    public CalcException() {
        super("Calculation error");
    }
    public CalcException(String message) {
        super(message);
    }
}
