package twoknightgame.console;

import game.console.TwoPhaseMoveGame;
import twoknightgame.model.*;

import twoknightgame.model.TwoKnightModel;

/**
 * Conducts the TwoKnight game on the console.
 */
public class TwoKnightConsole {

    public static void main(String[] args) {
        var state = new TwoKnightModel();
        var game = new TwoPhaseMoveGame<>(state, TwoKnightConsole::parseMove);
        game.start();
    }

    /**
     * Converts a string containing the position of a move to a {@code Position}
     * object.
     *
     * @param s a string that contains a letter 'a-h' for the column and a number '1-8' for the row
     * @return the {@code Position} object that was constructed from the string
     * @throws IllegalArgumentException if the format of the string is invalid,
     * i.e., its content is not two integers separated with spaces
     */
    public static Position parseMove(String s) {
        s = s.trim();
        if (!s.matches("[a-hA-H][1-8]")) {
            throw new IllegalArgumentException();
        }
        char letter = s.charAt(0);
        int number = Character.getNumericValue(s.charAt(1));
        int row = 8 - number;
        int col = letter - 'a';
        return new Position(row, col);
    }
}