package cli;

import core.*;
import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class OutputWriterTest {

    @Test
    public void ShouldPrintMowerwithAllCoordinates() {
        Mower mower = new Mower(2, 2, Orientation.S);
        String result = new OutputWriter().print(Arrays.asList(mower));
        assertEquals("2 2 S\n", result);
    }

    @Test
    public void ShouldPrintSeveralMowers() {
        Mower mower1 = new Mower(1, 2, Orientation.W);
        Mower mower2 = new Mower(3, 4, Orientation.E);
        String result = new OutputWriter().print(Arrays.asList(mower1, mower2));
        assertEquals("1 2 W\n3 4 E\n", result);
    }
}
