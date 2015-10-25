
package ru.spbstu.appmath.bazaliy;


public class ExpBuilder {

    private String expression; // Строка с исходным выражением
    private int p = 0; // текущая позиция
    private boolean valid;
    private ExpTree expTree;
    private static String[][] states = new String[][]{
            {"+", "-"},
            {"*", "/"},
            null
    };

    public ExpBuilder(String expression) throws Exception {
        this.expression = expression;
        valid = checkExpression();
        if (valid)
            expTree = build(0);
    }

    public final ExpTree getExp() {
        if (!valid)
            return null;
        return expTree;
    }

    private boolean checkExpression() {
        Character[] Symbols = new Character[]{
                '+', '-', '*', '/', ' ',
                '\t', '\n', 'x',
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', '(',')'
        };

        for (int i = 0; i < expression.length(); ++i) {
            boolean inSymbols = false;
            for (Character s : Symbols)
                if (s == expression.charAt(i))
                    inSymbols = true;
            if (!inSymbols)
                return false;
        }
        return true;
    }

    private ExpTree build(int state) throws Exception {
        if ((state + 1) >= states.length) {
            ExpTree ex = null;
            boolean isMinus = startWith("-");
            if (isMinus)
                skip("-");

            if (startWith("(")) {
                skip("(");
                ex = build(0);
                skip(")");
            } else
                ex = readSingle();
            if (isMinus)
                ex = new ExpTree.Unary(ex, "-");
            return ex;
        }
        //строим первый операнд
        ExpTree a1 = build(state + 1);
        // строим последущие операнды
        String op = null;
        while ((op = readStateOperator(state)) != null) {
            ExpTree a2 = build(state + 1);
            a1 = new ExpTree.Binary(a1, a2, op);
        }
        return a1;
    }

    private boolean startWith(String s) {
        return expression.startsWith(s, p);
    }

    private void skip(String s) {
        if (startWith(s))
            p += s.length();
        while (p < expression.length() && expression.charAt(p) == ' ')
            p++;
    }


    private String readStateOperator(int state) {
        String[] ops = states[state];
        for (String s : ops) {
            if (startWith(s)) {
                skip(s);
                return s;
            }
        }
        return null;
    }

    private ExpTree readSingle() throws Exception {
        int p0 = p;

        while (p < expression.length()) {
            if (!(Character.isLetterOrDigit(expression.charAt(p))))
                break;
            p++;
        }

        ExpTree ex = null;
        if (p > p0) {
            String s = expression.substring(p0, p);
            skip(" ");
            try {
                //пробуем прочитать число
                double x = Double.parseDouble(s);
                return new ExpTree.Num(x);
            } catch (Exception e) {
            }

            return new ExpTree.Var(s);

        }
        throw new Exception("Wrong syntax");
    }
}


