package core;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class MowerTest {

    @Before
    public void setup() {
        Lawn.maxOrdinate = 5;
        Lawn.maxAbsciss = 5;
        Lawn.occupiedPositions = new ArrayList<>();
    }

    @Test
    public void ShouldRotateLeft() {
        Mower mower = new Mower(2, 2, Orientation.N, Arrays.asList(Instruction.L));
        Lawn.occupiedPositions.add(new Position(2,2));
        mower.run();
        assertEquals(Orientation.W, mower.getOrientation());
    }

    @Test
    public void ShouldRotateRight() {
        Mower mower = new Mower(2, 2, Orientation.N, Arrays.asList(Instruction.R));
        Lawn.occupiedPositions.add(new Position(2,2));
        mower.run();
        assertEquals(Orientation.E, mower.getOrientation());
    }

    @Test
    public void ShouldMoveForward() {
        Mower mower = new Mower(2, 2, Orientation.W, Arrays.asList(Instruction.F));
        Lawn.occupiedPositions.add(new Position(2,2));
        mower.run();
        assertEquals(new Position (1, 2), mower.position);
    }

    @Test
    public void ShouldExecuteSeveralInstructions() {
        Mower mower = new Mower(2, 2, Orientation.S, Arrays.asList(Instruction.F, Instruction.L, Instruction.F));
        Lawn.occupiedPositions.add(new Position(2,2));
        mower.run();
        assertEquals(new Position(3, 1), mower.position);
    }

    @Test
    public void ShouldNotExceedLawnLimits() {
        Mower mower = new Mower(5, 5, Orientation.N, Arrays.asList(Instruction.F, Instruction.R, Instruction.F));
        Lawn.occupiedPositions.add(new Position(5,5));
        mower.run();
        assertEquals(new Position(5, 5), mower.position);
    }

    @Test
    public void ShouldNotMoveToOccupiedPosition() {
        Mower mower = new Mower(2, 2, Orientation.N, Arrays.asList(Instruction.F));
        Lawn.occupiedPositions.add(new Position(2,2));
        Lawn.occupiedPositions.add(new Position(2,3));
        mower.run();
        assertEquals(new Position (2, 2), mower.position);
        assertEquals(2, Lawn.occupiedPositions.size());
    }
}
