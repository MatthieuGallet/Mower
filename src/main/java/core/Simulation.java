package core;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    List<Mower> mowers;

    public Simulation(List<Mower> mowers) {
        this.mowers = mowers;
        mowers.forEach(mower -> {
            validateMowerPosition(mower.position);
            Lawn.occupiedPositions.add(mower.position);
        });
    }

    private void validateMowerPosition(Position position) {
        if (Lawn.occupiedPositions.contains(position)
                || position.getAbsciss() > Lawn.maxAbsciss || position.getAbsciss() < 0
                || position.getOrdinate() > Lawn.maxOrdinate || position.getOrdinate() < 0) {
            System.out.println("Mower initially outside of lawn limits or at the same position of another mower.");
            throw new IllegalArgumentException();
        }
    }

    public void run() {
        List<Thread> threads = new ArrayList<>();
        mowers.forEach(mower -> {
            Thread newThread = new Thread(mower);
            newThread.start();
            threads.add(newThread);
        });
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
