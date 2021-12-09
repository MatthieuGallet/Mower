package core;

import java.util.List;
import java.util.Optional;

public class Mower implements Runnable {

    Position position;
    private Orientation orientation;

    private List<Instruction> instructions;

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public Mower(int absciss, int ordinate, Orientation orientation, List<Instruction> instructions) {
        this.position = new Position(absciss, ordinate);
        this.orientation = orientation;
        this.instructions = instructions;
    }

    public Mower(int absciss, int ordinate, Orientation orientation) {
        this.position = new Position(absciss, ordinate);
        this.orientation = orientation;
    }

    private Optional<Position> nextNorth() {
        return (this.position.ordinate == Lawn.maxOrdinate)
                ? Optional.empty()
                : Optional.of(new Position(this.position.absciss, this.position.ordinate + 1));
    }

    private Optional<Position> nextSouth() {
        return (this.position.ordinate == 0)
                ? Optional.empty()
                : Optional.of(new Position(this.position.absciss, this.position.ordinate - 1));
    }

    private Optional<Position> nextEast() {
        return (this.position.absciss == Lawn.maxAbsciss)
                ? Optional.empty()
                : Optional.of(new Position(this.position.absciss + 1, this.position.ordinate));
    }

    private Optional<Position> nextWest() {
        return (this.position.absciss == 0)
                ? Optional.empty()
                : Optional.of(new Position(this.position.absciss - 1, this.position.ordinate));
    }

    private Optional<Position> nextPosition() {
        switch (this.getOrientation()) {
            case N:
                return this.nextNorth();
            case E:
                return this.nextEast();
            case S:
                return this.nextSouth();
            case W:
                return this.nextWest();
        }
        return null;
    }

    private void moveForward() {
        Optional<Position> possiblePosition = nextPosition();
        possiblePosition.ifPresent(nextPosition -> {
            synchronized (Lawn.occupiedPositions) {
                if (!Lawn.occupiedPositions.contains(nextPosition)) {
                    Lawn.occupiedPositions.add(nextPosition);
                    Position previousPosition = this.position;
                    this.position = nextPosition;
                    Lawn.occupiedPositions.remove(previousPosition);
                }
            }
        });
    }

    private void move(Instruction instruction) {
        switch (instruction) {
            case L:
                this.setOrientation(this.orientation.turnLeft());
                break;
            case R:
                this.setOrientation(this.orientation.turnRight());
                break;
            case F:
                this.moveForward();
                break;
        }
    }

    @Override
    public void run() {
        instructions.forEach(this::move);
    }


}
