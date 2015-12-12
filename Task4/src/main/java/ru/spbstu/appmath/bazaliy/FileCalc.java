package ru.spbstu.appmath.bazaliy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

class Limits {
    Limits(String input) throws IOException {
        String[] arrLimits = input.split(":");
        if (arrLimits.length == 3) {
            try {
                this.min = Double.parseDouble(arrLimits[0]);
                this.max = Double.parseDouble(arrLimits[1]);
                this.step = Double.parseDouble(arrLimits[2]);
            } catch (NumberFormatException e) {
                throw new IOException("Wrong format");
            }
        } else if (arrLimits.length == 2) {
            try {
                this.min = Double.parseDouble(arrLimits[0]);
                this.max = Double.parseDouble(arrLimits[1]);
                this.step = 1;
            } catch (NumberFormatException e) {
                throw new IOException("Wrong format");
            }
        } else
            throw new IOException("Wrong format");
    }
    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getStep() {
        return step;
    }

    private final double min;
    private final double max;
    private final double step;


}

public class FileCalc {

    public FileCalc(String inputFile,String outputFile, Limits limits) throws IOException{
        this(inputFile,outputFile,limits.getMin(),limits.getMax(),limits.getStep());
    }
    public FileCalc(String inputFile, String outputFile, double min, double max) throws IOException {
        this(inputFile, outputFile, min, max, 1);
    }
    public FileCalc(String inputFile, String outputFile, double min, double max, double step) throws IOException {
        if (min > max)
            throw new IOException("Wrong limits");
        this.min = min;
        this.max = max;
        this.step = step;
        this.inFile = new File(inputFile);
        this.outFile = new File(outputFile);
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
    }

    /*
    * Set length for the strings, to get nice output
    * */
    private static String getFormat(Vector<String> expressions) {
        int length = MAX_EXCEPTION_LENGTH;
        for (String e : expressions) {
            if (e.length() > length)
                length = e.length();
        }
        return "%" + Integer.toString(length + "\t".length()) + "s";
    }


    public void Execute() throws Exception {
        Vector<String> expressions = getExpressions();
        String format = getFormat(expressions);
        PrintWriter pw = new PrintWriter(outFile.getAbsoluteFile());

        pw.printf(format, "x");
        for (String e : expressions) {
            pw.printf(format, e);
        }
        pw.println();

        for (Double value = min; value < max; value += step) {
            pw.printf(format, value.toString());
            for (String e : expressions) {
                try {
                    ExpTree expTree = new ExpBuilder(e).build();
                    Double result = expTree.execute(value);
                    pw.printf(format, result.toString());
                } catch (Exception ex) {
                    pw.printf(format, ex.getMessage());
                }
            }
            pw.println();
        }
        pw.close();
    }

    private Vector<String> getExpressions() throws Exception {
        Scanner scanner = new Scanner(inFile);
        Vector<String> expressions = new Vector<String>();
        while (scanner.hasNextLine()) {
            expressions.add(scanner.nextLine());
        }
        scanner.close();
        return expressions;
    }

    private double min;
    private double max;
    private double step;
    private File inFile;
    private File outFile;
    private static final int MAX_EXCEPTION_LENGTH = 27;

}
