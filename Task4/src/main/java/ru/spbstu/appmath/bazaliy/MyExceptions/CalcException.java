package ru.spbstu.appmath.bazaliy.MyExceptions;

public class CalcException extends Exception {
    public CalcException() {
        super("Calculation error");
    }

    public CalcException(String message) {
        super(message);
    }
}

