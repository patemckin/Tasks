package ru.spbstu.appmath.bazaliyv;

import ru.spbstu.appmath.bazaliyv.exceptions.MatrixCalcException;
import ru.spbstu.appmath.bazaliyv.exceptions.ParserException;

import static ru.spbstu.appmath.bazaliyv.IOMatrix.getMatrixFromFile;

/**
 * Created by admin on 12/12/15.
 */

public class MatrixCalc {

    private static class Parser {
        private final String inputFirst;
        private final String inputSecond;
        private final String outputFile;
        private final int threads;

        public Parser(final String args[]) throws ParserException {
            if (args.length > 5)
                throw new ParserException("There's too much argumets");
            if (args.length < 3)
                throw new ParserException("There's not enough argumets");
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
    }

    public static void main(String[] args) {
        try {
            Parser parser = new Parser(args);
            Matrix matrix1 = getMatrixFromFile(parser.getInputFirst());
            Matrix matrix2 = getMatrixFromFile(parser.getInputSecond());
            Matrix result = new MatrixMultiplication(matrix1, matrix2, parser.getThreads()).multiply();
            IOMatrix.printInFile(result, parser.getOutputFile());
        } catch (MatrixCalcException e) {
            System.out.println(e.getMessage());
        }

    }
}