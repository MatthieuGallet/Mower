package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lawn {
    public static int maxAbsciss;
    public static int maxOrdinate;

    /**
     * List of all the position currently occupied by the mowers.
     * We use a synchronized list to allow the mowers to lock the list while they modify it.
     */
    static List<Position> occupiedPositions = Collections.synchronizedList(new ArrayList<>());
}
