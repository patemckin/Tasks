package ru.spbstu.appmath.bazaliy;

import ru.spbstu.appmath.bazaliy.exceptions.rangecalcexceptions.LimitsException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileCalc {

    private static final int MAX_EXCEPTION_LENGTH = 27;
    private final double min;
    private final double max;
    private final double step;
    private final File inFile;
    private final File outFile;

    public FileCalc(final String inputFile, final String outputFile, final Limits limits) throws LimitsException, IOException {
        this(inputFile, outputFile, limits.getMin(), limits.getMax(), limits.getStep());
    }

    public FileCalc(final String inputFile, final String outputFile, final double min, final double max, final double step) throws LimitsException, IOException {
        if (min > max)
            throw new LimitsException();
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
    private static String getFormat(final ArrayList<String> expressions) {
        int length = MAX_EXCEPTION_LENGTH;
        for (String e : expressions) {
            if (e.length() > length)
                length = e.length();
        }
        return "%" + Integer.toString(length + "\t".length()) + "s";
    }


    public void Execute() throws IOException {
        ArrayList<String> expressions = getExpressions();
        String format = getFormat(expressions);
        try(PrintWriter pw = new PrintWriter(outFile.getAbsoluteFile())) {

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
    }

    private ArrayList<String> getExpressions() throws IOException {
        try(Scanner scanner = new Scanner(inFile)) {
            ArrayList<String> expressions = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                expressions.add(scanner.nextLine());
            }
            scanner.close();
            return expressions;
        }
    }

}
