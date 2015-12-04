package ru.spbstu.appmath.bazaliy.MyExceptions;

import java.io.IOException;

public class VariableException extends IOException {
        public VariableException() {
            super("Problems with variable processing");
        }
        public VariableException(String message) {
            super(message);
        }
        public VariableException(String message, Throwable cause) {
            super(message, cause);
        }
}
