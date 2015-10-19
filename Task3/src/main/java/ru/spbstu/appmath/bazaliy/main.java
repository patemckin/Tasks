package ru.spbstu.appmath.bazaliy;

public class main {
    public static void main(String[] args) throws Exception {
        //Scanner input= new Scanner(System.in);
        //String str=input.next();
        String expression = args[0];
        Double value;
        if (args.length > 1) {
            try{
                value = Double.parseDouble(args[1]);
            } catch (Exception e){}

        }

    }
}
