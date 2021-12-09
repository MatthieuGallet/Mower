package core;

public enum Orientation {
    N,  // North
    E,  // East
    W,  // West
    S;  // South

    Orientation turnLeft() {
        switch (this) {
            case N:
                return W;
            case E:
                return N;
            case S:
                return E;
            case W:
                return S;
            default:
                return N;
        }
    }

    Orientation turnRight() {
        switch (this) {
            case N:
                return E;
            case E:
                return S;
            case S:
                return W;
            case W:
                return N;
            default:
                return N;
        }
    }
}
