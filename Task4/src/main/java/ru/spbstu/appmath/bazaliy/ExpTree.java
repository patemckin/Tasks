package ru.spbstu.appmath.bazaliy;

import ru.spbstu.appmath.bazaliy.exceptions.calcexceptions.CalculationException;


public interface ExpTree {

    double execute(final double variable) throws CalculationException;

    boolean hasVar();

    class Num implements ExpTree {
        private final double value;

        public Num(double x) {
            value = x;
        }

        public double execute(final double variable) throws CalculationException{
            return value;
        }
        public boolean hasVar(){ return false;}
    }

    class Var implements ExpTree {
        private final String name;

        public Var(String name) {
            this.name = name;
        }

        public double execute(final double variable) throws CalculationException{
            return variable;
        }
        public boolean hasVar(){ return true;}
    }

    class Unary implements ExpTree {
        private final ExpTree expr;

        public Unary(ExpTree e) {
            expr = e;
        }

        public double execute(final double variable) throws CalculationException{
            double o = expr.execute(variable);
            return -o;
        }

        public boolean hasVar(){ return false;}
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

        public double execute(final double variable) throws CalculationException{
            double o1 = x1.execute(variable);
            double o2 = x2.execute(variable);
            return execNum(o1, o2);
        }

        public boolean hasVar(){ return x1.hasVar() || x2.hasVar(); }

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
