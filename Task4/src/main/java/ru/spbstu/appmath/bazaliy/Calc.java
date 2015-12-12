package ru.spbstu.appmath.bazaliy;

public class Calc {
    public static void main(String[] args) {
        try {
            FileCalc calculator = new FileCalc("input.txt", "output.txt", new Limits("1:4:0.5"));
            calculator.Execute();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }




}
