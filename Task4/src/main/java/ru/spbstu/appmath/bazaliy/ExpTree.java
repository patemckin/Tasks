package ru.spbstu.appmath.bazaliy;

import ru.spbstu.appmath.bazaliy.MyExceptions.*;


public interface ExpTree {

    double execute(double variable) throws CalcException;

    double execute() throws CalcException;

    class Num implements ExpTree {
        private final double value;

        public Num(double x) {
            value = x;
        }

        public double execute(double variable) {
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

        public double execute(double variable) {
            return variable;
        }

        public double execute() throws CalcException {
            throw new VariableException();
        }
    }

    class Unary implements ExpTree {
        private final ExpTree expr;

        public Unary(ExpTree e) {
            expr = e;
        }

        public double execute(double variable) throws CalcException {
            double o = expr.execute(variable);
            return -o;
        }

        public double execute() throws CalcException {
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

        public double execute(double variable) throws CalcException {
            double o1 = x1.execute(variable);
            double o2 = x2.execute(variable);
            return execNum(o1, o2);
        }

        public double execute() throws CalcException {
            double o1 = x1.execute();
            double o2 = x2.execute();
            return execNum(o1, o2);
        }

        private double execNum(double n1, double n2) throws CalcException {
            if ("+".equals(op))
                return (n1 + n2);
            if ("-".equals(op))
                return (n1 - n2);
            if ("*".equals(op))
                return (n1 * n2);
            if ("/".equals(op)) {
                if (n2 == 0)
                    throw new CalcException("Division by zero");
                return (n1 / n2);
            }
            throw new CalcException("Illegal double operator: " + op);
        }
    }
}
