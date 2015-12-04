package ru.spbstu.appmath.bazaliy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by admin on 05/12/15.
 */
public class FileCalc {


    public FileCalc(String inputFile, String outputFile, double min, double max) throws IOException{
        this(inputFile,outputFile,min,max,1);
    }
    public FileCalc(String inputFile, String outputFile, double min, double max, double step) throws IOException{
        this.min = min;
        this.max = max;
        this.step = step;
        this.inFile = new File(inputFile);
        this.outFile = new File(outputFile);
        if (!outFile.exists())
        {
            outFile.createNewFile();
        }
    }


    public void Execute() throws Exception {
        Vector<String> expressions = getExperssions();
        System.out.print(expressions);
        PrintWriter pw = new PrintWriter(outFile.getAbsoluteFile());

        pw.print("x\t");
        for (String e: expressions)
            pw.print(e+"\t");
        pw.println();

        for (double value = min; value < max; value+=step )
        {
            pw.print(value+"\t");
            for (String e: expressions)
            {
                try{
                    ExpTree expTree = new ExpBuilder(e).build();
                    double result = expTree.execute(value);
                    pw.print(result+"\t");
                }
                catch(Exception ex)
                {
                    pw.print(ex.getMessage()+"\t");
                }
            }
            pw.println();
        }
        pw.close();
    }
    private Vector<String> getExperssions() throws Exception{
        Scanner scanner = new Scanner(inFile.getAbsoluteFile());
        Vector<String> expressions = new Vector<String>();
        while(scanner.hasNextLine())
        {
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


}
