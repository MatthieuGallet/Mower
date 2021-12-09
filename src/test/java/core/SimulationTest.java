package core;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SimulationTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        Lawn.maxOrdinate = 5;
        Lawn.maxAbsciss = 5;
        Lawn.occupiedPositions = Collections.synchronizedList(new ArrayList<>());
    }

    @Test
    public void ShouldMoveSeveralMowers() {
        Mower mower1 = new Mower(2, 2, Orientation.N, Arrays.asList(Instruction.F));
        Mower mower2 = new Mower(1, 1, Orientation.N, Arrays.asList(Instruction.F));
        Simulation simulation = new Simulation(Arrays.asList(mower1, mower2));
        simulation.run();
        assertEquals(3, mower1.getPosition().ordinate);
        assertEquals(2, mower2.getPosition().ordinate);
    }

    @Test
    public void ShouldReject2MowersAtTheSameInitialPosition() {
        Mower mower1 = new Mower(1, 1, Orientation.N);
        Mower mower2 = new Mower(1, 1, Orientation.S);
        thrown.expect(IllegalArgumentException.class);
        new Simulation(Arrays.asList(mower1, mower2));
    }

    @Test
    public void ShouldRejectMowerOutsideOfLawnNE() {
        Mower mower1 = new Mower(6, 1, Orientation.N);
        thrown.expect(IllegalArgumentException.class);
        new Simulation(Arrays.asList(mower1));
    }

    @Test
    public void ShouldRejectMowerOutsideOfLawnSW() {
        Mower mower1 = new Mower(-1, 1, Orientation.N);
        thrown.expect(IllegalArgumentException.class);
        new Simulation(Arrays.asList(mower1));
    }
}
