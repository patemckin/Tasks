package ru.spbstu.appmath.bazaliy;

public class Calc {
    public static void main(String[] args){
        int c = 0;
        for(int i = 0; i < 9;i++ ){
            for(int j = 0; j < 9;j++ ) {
                for(int k = 0; k < 9;k++ ) {
                    if ((i + 1) * (j + 1) * (k + 1) == 2 * i * j * k)
                    {    System.out.println(i + " " + j + " " + k);
                    c++;}
                }
            }
        }
        System.out.println(c);

        try {
            String expression = args[0];
            Double value = 0.0;
            boolean isVariableInited = false;

            if (args.length > 2 || args.length == 0)
                throw new Exception("Too much arguments");
            if (args.length == 2) {
                value = Double.parseDouble(args[1]);
                isVariableInited = true;
            }
            ExpBuilder exp = new ExpBuilder(expression,isVariableInited);
            ExpTree expTree = exp.getExp();
            Double result = expTree.execute(value);
            //System.out.print(result);
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
