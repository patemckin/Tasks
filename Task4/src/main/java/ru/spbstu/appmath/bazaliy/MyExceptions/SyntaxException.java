package ru.spbstu.appmath.bazaliy.MyExceptions;

import java.io.IOException;

public class SyntaxException extends IOException {
    public SyntaxException() {
        super("Wrong syntax");
    }

    public SyntaxException(String message) {
        super(message);
    }
}

