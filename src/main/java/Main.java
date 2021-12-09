import cli.InputReader;
import cli.OutputWriter;
import core.Mower;
import core.Simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            // Parsing the argument of the CLI
            if (args.length != 2) {
                System.out.println("Wrong number of argument. There should be only 2 text file");
                return;
            }
            String inputFilePath = args[0];
            String outputFilePath = args[1];

            // Scanning the input file to fill the list of mowers and instantiate the lawn.
            Scanner sc = new Scanner(new File(inputFilePath));
            List<Mower> mowers = new InputReader().readInput(sc);
            sc.close();

            // Run the simulation
            new Simulation(mowers).run();

            // Write the output file
            String outputText = new OutputWriter().print(mowers);
            PrintWriter out = new PrintWriter(outputFilePath);
            out.print(outputText);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
