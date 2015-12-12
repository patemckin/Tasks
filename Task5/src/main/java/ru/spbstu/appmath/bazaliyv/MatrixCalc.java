package ru.spbstu.appmath.bazaliyv;

import java.io.IOException;

/**
 * Created by admin on 12/12/15.
 */

public class MatrixCalc {

    private static class Parser {
        public Parser(final String args[]) throws IOException {
            if (args.length > 5)
                throw new IOException("There's too much argumets");
            if (args.length < 3)
                throw new IOException("There's not enough argumets");
            this.inputFirst = args[0];
            this.inputSecond = args[1];
            this.outputFile = args[2];
            if (args.length == 3)
                this.threads = 1;
            else
                this.threads = Integer.parseInt(args[3]);
        }

        public String getInputFirst() {
            return inputFirst;
        }

        public String getInputSecond() {
            return inputSecond;
        }

        public String getOutputFile() {
            return outputFile;
        }

        public int getThreads() {
            return threads;
        }

        private final String inputFirst;
        private final String inputSecond;
        private final String outputFile;
        private final int threads;
    }

    public static void main(String[] args) {
        try {
            Parser parser = new Parser(args);
            Matrix matrix1 = new Matrix(parser.getInputFirst());
            Matrix matrix2 = new Matrix(parser.getInputSecond());
            Matrix result = new MultiplyMatrix(matrix1, matrix2, parser.getThreads()).multiply();
            result.printInFile(parser.getOutputFile());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}