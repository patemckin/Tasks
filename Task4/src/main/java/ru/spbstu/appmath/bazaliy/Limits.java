package ru.spbstu.appmath.bazaliy;

import ru.spbstu.appmath.bazaliy.exceptions.rangecalcexceptions.FormatException;

/**
 * Created by admin on 24/12/15.
 */
class Limits {
    private final double min;
    private final double max;
    private final double step;

    Limits(final String input) throws FormatException {
        String[] arrLimits = input.split(":");
        if (arrLimits.length == 3) {
            try {
                this.min = Double.parseDouble(arrLimits[0]);
                this.max = Double.parseDouble(arrLimits[1]);
                this.step = Double.parseDouble(arrLimits[2]);
            } catch (NumberFormatException e) {
                throw new FormatException("Problems with parsing numbers");
            }
        } else if (arrLimits.length == 2) {
            try {
                this.min = Double.parseDouble(arrLimits[0]);
                this.max = Double.parseDouble(arrLimits[1]);
                this.step = 1;
            } catch (NumberFormatException e) {
                throw new FormatException("Problems with parsing numbers");
            }
        } else
            throw new FormatException("Different number of arguments");
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStep() {
        return step;
    }

}
