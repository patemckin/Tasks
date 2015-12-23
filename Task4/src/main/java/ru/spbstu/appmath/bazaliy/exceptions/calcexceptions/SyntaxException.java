package ru.spbstu.appmath.bazaliy.exceptions.calcexceptions;

public class SyntaxException extends CalculatorException {
    public SyntaxException() {
        super("Wrong syntax");
    }

    public SyntaxException(final String message) {
        super(message);
    }
}

