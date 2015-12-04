
package ru.spbstu.appmath.bazaliy;


import ru.spbstu.appmath.bazaliy.MyExceptions.SyntaxException;

public class ExpBuilder {

    private static String[][] states = new String[][]{
            {"+", "-"},
            {"*", "/"},
            null
    };
    private String expression; // input expression
    private int pos = 0; // current pos
    private ExpTree expTree; // expression tree

    public ExpBuilder(String expression) throws Exception {
        this.expression = expression;
        checkExpression();
    }

    public ExpTree build() throws Exception {
        this.expTree = makeTree(0);
        return getExp();
    }

    public final ExpTree getExp() {
        return expTree;
    }

    private void checkExpression() throws Exception {
        Character[] Symbols = new Character[]{
                '+', '-', '*', '/',
                ' ', '\t', '\n', 'x',
                '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', '(',')',','
        };

        for (int i = 0; i < expression.length(); ++i) {
            boolean inSymbols = false;
            for (Character s : Symbols)
                if (s == expression.charAt(i)) {
                    inSymbols = true;
                }
            if (!inSymbols)
                throw new SyntaxException("Wrong symbols in expression");
        }
    }

    private ExpTree makeTree(int state) throws Exception {
        if ((state + 1) >= states.length) {
            ExpTree ex;
            boolean isMinus = startWith("-");
            if (isMinus)
                skip("-");

            if (startWith("(")) {
                skip("(");
                ex = makeTree(0);
                skip(")");
            } else
                ex = readSingle();
            if (isMinus)
                ex = new ExpTree.Unary(ex);
            return ex;
        }

        ExpTree a1 = makeTree(state + 1);
        String op;
        while ((op = readStateOperator(state)) != null) {
            ExpTree a2 = makeTree(state + 1);
            a1 = new ExpTree.Binary(a1, a2, op);
        }
        return a1;
    }

    private boolean startWith(String s) {
        return expression.startsWith(s, pos);
    }

    private void skip(String s) {
        if (startWith(s))
            pos += s.length();
        while (pos < expression.length() && expression.charAt(pos) == ' ')
            pos++;
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
        int p0 = pos;

        while (pos < expression.length()) {
            if (!Character.isLetterOrDigit(expression.charAt(pos)) && (expression.charAt(pos) != '.'))
                break;
            pos++;
        }
        if (pos > p0) {
            String s = expression.substring(p0, pos);
            skip(" ");
            try {
                double x = Double.parseDouble(s);
                return new ExpTree.Num(x);
            } catch (Exception e) {
            }

            return new ExpTree.Var(s);

        }
        throw new SyntaxException();
    }
}


