package cli;

import core.Mower;
import core.Position;

import java.util.List;

public class OutputWriter {

    public String print(List<Mower> mowers) {
        String outputText = "";
        for (Mower mower: mowers) {
            outputText += printMower(mower) + "\n";
        }
        return outputText;
    }

    private String printMower(Mower mower) {
        return printPosition(mower.getPosition()) + " " + mower.getOrientation().toString();
    }

    private String printPosition(Position position) {
        return position.getAbsciss() + " " + position.getOrdinate();
    }
}
