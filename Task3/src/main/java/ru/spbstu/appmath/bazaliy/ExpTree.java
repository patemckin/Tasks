package ru.spbstu.appmath.bazaliy;

/**
 * Вычислимое Выражение
 */
public abstract class ExpTree {

    public abstract Double execute(Double variable) throws Exception;


    static class Num extends ExpTree {
        private final double value;

        public Num(double x) {
            value = x;
        }

        @Override
        public Double execute(Double variable) {
            return value;
        }
    }

    static class Var extends ExpTree {
        private final String name;

        public Var(String name) {
            this.name = name;
        }

        @Override
        public Double execute(Double variable) {
            return variable;
        }
    }

    static class Unary extends ExpTree {
        private final ExpTree expr;

        public Unary(ExpTree e, String oper) {
            expr = e;
        }

        @Override
        public Double execute(Double variable) throws Exception {
            Double o = expr.execute(variable);
            return -(Double)o;
        }
    }

    static class Binary extends ExpTree {
        private final ExpTree x1;
        private final ExpTree x2;
        private final String op;

        public Binary(ExpTree x1, ExpTree x2, String op) {
            this.x1 = x1;
            this.x2 = x2;
            this.op = op;
        }

        @Override
        public Double execute(Double variable) throws Exception {
            Double o1 = x1.execute(variable);
            Double o2 = x2.execute(variable);
            return execNum(o1,o2);
        }

        private Double execNum(double n1, double n2) throws Exception {
            if ("+".equals(op))
                return (Double)(n1 + n2);
            if ("-".equals(op))
                return (Double)(n1 - n2);
            if ("*".equals(op))
                return (Double)(n1 * n2);
            if ("/".equals(op))
                return (Double)(n1 / n2);

            throw new Exception("Illegal Double operator: " + op);
        }
    }
}
