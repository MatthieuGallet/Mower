package cli;

import core.Instruction;
import core.Lawn;
import core.Mower;
import core.Orientation;

import java.util.*;

public class InputReader {

    public List<Mower> readInput(Scanner sc) {
        List<Mower> mowers = new ArrayList<>();
        try {
            Lawn.maxAbsciss = sc.nextInt();
            Lawn.maxOrdinate = sc.nextInt();
        } catch (NoSuchElementException e) {
            System.out.println("Invalid lawn description");
            throw e;
        }
        while (sc.hasNextLine()) {
            Mower newMower = new Mower(sc.nextInt(), sc.nextInt(), Orientation.valueOf(sc.next()));
            mowers.add(newMower);
            sc.nextLine(); // Needed to skip the rest of the first line.
            String instructionsString = sc.nextLine();
            newMower.setInstructions(parseInstructionString(instructionsString));
        }
        return mowers;
    }

    private List<Instruction> parseInstructionString(String instructionString) {
        List<Instruction> instructions = new ArrayList<>();
        try {
            for (String c: instructionString.split("")) {
                instructions.add(Instruction.valueOf(c));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid instruction in the input file.");
            throw e;
        }
        return instructions;
    }
}
