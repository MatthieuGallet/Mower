import core.*;
import cli.InputReader;
import cli.OutputWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("Wrong number of argument. There should be only 2 text file");
                return;
            }
            String inputFilePath = args[0];
            String outputFilePath = args[1];

            Scanner sc = new Scanner(new File(inputFilePath));
            List<Mower> mowers = new InputReader().readInput(sc);
            sc.close();

            new Simulation(mowers).run();

            String outputText = new OutputWriter().print(mowers);

            PrintWriter out = new PrintWriter(outputFilePath);
            out.print(outputText);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
