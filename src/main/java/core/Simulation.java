package core;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    List<Mower> mowers;

    public Simulation(List<Mower> mowers) {
        this.mowers = mowers;
        mowers.forEach(mower -> {
            if (Lawn.occupiedPositions.contains(mower.position)) throw new IllegalArgumentException();
            Lawn.occupiedPositions.add(mower.position);
        });
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
