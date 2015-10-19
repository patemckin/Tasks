package ru.spbstu.appmath.bazaliy;

/**
 * Вычислимое Выражение
 */
public abstract class ExpTree {

    public abstract Object execute(Double variable) throws Exception;


    static class Num extends ExpTree {
        private final double value;

        public Num(double x) {
            value = x;
        }

        @Override
        public Object execute(Double variable) {
            return value;
        }
    }

    static class Var extends ExpTree {
        private final String name;

        public Var(String name) {
            this.name = name;
        }

        @Override
        public Object execute(Double variable) {
            return variable;
        }
    }

    static class Unary extends ExpTree {
        private final ExpTree expr;

        public Unary(ExpTree e, String oper) {
            expr = e;
        }

        @Override
        public Object execute(Double variable) throws Exception {
            Object o = expr.execute(variable);
                return -(Long)o;
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
        public Object execute(Double variable) throws Exception {
            Object o1 = x1.execute(variable);
            Object o2 = x2.execute(variable);
            return execNum((Long)o1, (Long)o2);
        }

        private Object execNum(long n1, long n2) throws Exception {
            if("+".equals(op))
                return (Long)(n1 + n2);
            if("-".equals(op))
                return (Long)(n1 - n2);
            if("*".equals(op))
                return (Long)(n1 * n2);
            if("/".equals(op))
                return (Long)(n1 / n2);

            throw new Exception("Illegal Long operator: " + op);
        }
    }
}
