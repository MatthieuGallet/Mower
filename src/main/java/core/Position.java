package core;

public class Position {
    int absciss;
    int ordinate;

    public int getAbsciss() {
        return absciss;
    }

    public int getOrdinate() {
        return ordinate;
    }

    public Position(int absciss, int ordinate) {
        this.absciss = absciss;
        this.ordinate = ordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) {
            return false;
        }
        Position p = (Position) o;
        return this.absciss == p.absciss && this.ordinate == p.ordinate;
    }
}
