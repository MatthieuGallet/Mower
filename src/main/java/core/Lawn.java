package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lawn {
    public static int maxAbsciss;
    public static int maxOrdinate;
    static List<Position> occupiedPositions = Collections.synchronizedList(new ArrayList<>());
}
