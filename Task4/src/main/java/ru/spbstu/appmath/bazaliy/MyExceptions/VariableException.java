package ru.spbstu.appmath.bazaliy.MyExceptions;

public class VariableException extends CalcException {
    public VariableException() {
        super("Problems with variable processing");
    }

    public VariableException(String message) {
        super(message);
    }
}
