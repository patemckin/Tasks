package ru.spbstu.appmath.bazaliyv.exceptions;

/**
 * Created by admin on 24/12/15.
 */
public class DimensionsException extends MatrixCalcException{
    public DimensionsException() {
        super("File doesn't contain valid data");
    }
}
