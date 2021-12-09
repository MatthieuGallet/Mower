package cli;

import static org.junit.Assert.*;

import core.Instruction;
import core.Lawn;
import core.Mower;
import core.Position;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class InputReaderTest {

    Scanner sc;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
    }

    @Test
    public void ShouldRejectEmptyFile() {
        sc = new Scanner("");
        thrown.expect(NoSuchElementException.class);
        new InputReader().readInput(sc);
    }

    @Test
    public void ShouldRejectWrongFirstLine() {
        sc = new Scanner("5 ");
        thrown.expect(NoSuchElementException.class);
        new InputReader().readInput(sc);
    }

    @Test
    public void ShouldReadLawnLimits() {
        sc = new Scanner("5 4");
        List<Mower> result = new InputReader().readInput(sc);
        assertEquals(5, Lawn.maxAbsciss);
        assertEquals(4, Lawn.maxOrdinate);
        assertEquals(0, result.size());
    }

    @Test
    public void ShouldRejectHalfMower() {
        sc = new Scanner("5 4\n1 2 N");
        thrown.expect(NoSuchElementException.class);
        new InputReader().readInput(sc);
    }

    @Test
    public void ShouldRejectMowerWithMissingCoordinate() {
        sc = new Scanner("5 4\n1 N\nLF");
        thrown.expect(NoSuchElementException.class);
        new InputReader().readInput(sc);
    }

    @Test
    public void ShouldRejectMowerWithMissingInstructions() {
        sc = new Scanner("5 4\n1 2 N\n2 3 W");
        thrown.expect(IllegalArgumentException.class);
        new InputReader().readInput(sc);
    }

    @Test
    public void ShouldCreateSeveralMowers() {
        sc = new Scanner("5 4\n1 2 N\nLF\n2 3 S\nRF");
        List<Mower> result = new InputReader().readInput(sc);
        assertEquals(2, result.size());
        assertEquals(new Position(1, 2), result.get(0).getPosition());
    }

    @Test
    public void ShouldParseInstructions() {
        sc = new Scanner("5 4\n1 2 N\nLFR");
        List<Mower> result = new InputReader().readInput(sc);
        assertEquals(1, result.size());
        assertEquals(Arrays.asList(Instruction.L, Instruction.F, Instruction.R),
                result.get(0).getInstructions());
    }
}
