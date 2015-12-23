package ru.spbstu.appmath.bazaliy;

import ru.spbstu.appmath.bazaliy.exceptions.calcexceptions.CalculationException;


public interface ExpTree {

    double execute(final double variable) throws CalculationException;

    double execute() throws CalculationException;

    class Num implements ExpTree {
        private final double value;

        public Num(double x) {
            value = x;
        }

        public double execute(final double variable) {
            return value;
        }

        public double execute() {
            return value;
        }

    }

    class Var implements ExpTree {
        private final String name;

        public Var(String name) {
            this.name = name;
        }

        public double execute(final double variable) {
            return variable;
        }

        public double execute() throws CalculationException {
            throw new CalculationException("Expression doesn't contain value of the variable");
        }
    }

    class Unary implements ExpTree {
        private final ExpTree expr;

        public Unary(ExpTree e) {
            expr = e;
        }

        public double execute(final double variable) throws CalculationException {
            double o = expr.execute(variable);
            return -o;
        }

        public double execute() throws CalculationException {
            double o = expr.execute();
            return -o;
        }
    }


    class Binary implements ExpTree {
        private final ExpTree x1;
        private final ExpTree x2;
        private final String op;

        public Binary(ExpTree x1, ExpTree x2, String op) {
            this.x1 = x1;
            this.x2 = x2;
            this.op = op;
        }

        public double execute(final double variable) throws CalculationException {
            double o1 = x1.execute(variable);
            double o2 = x2.execute(variable);
            return execNum(o1, o2);
        }

        public double execute() throws CalculationException {
            double o1 = x1.execute();
            double o2 = x2.execute();
            return execNum(o1, o2);
        }

        private double execNum(final double n1, final double n2) throws CalculationException {
            if ("+".equals(op))
                return (n1 + n2);
            if ("-".equals(op))
                return (n1 - n2);
            if ("*".equals(op))
                return (n1 * n2);
            if ("/".equals(op)) {
                if (n2 == 0)
                    throw new CalculationException("Division by zero");
                return (n1 / n2);
            }
            throw new CalculationException("Illegal double operator: " + op);
        }
    }
}
