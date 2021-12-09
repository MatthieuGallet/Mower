package core;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    List<Mower> mowers;

    public Simulation(List<Mower> mowers) {
        this.mowers = mowers;
        mowers.forEach(mower -> {
            validateMowerPosition(mower.getPosition());
            Lawn.occupiedPositions.add(mower.getPosition());
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
        // Ensure all the mowers have completed their instructions before ending the simulation.
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
