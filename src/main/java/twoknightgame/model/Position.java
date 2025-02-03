package twoknightgame.model;

/**
 * Represents a position on the chess board.
 *
 * @param row the row of the position
 * @param col the column of the position
 */
public record Position(int row, int col) {
    /**
     * Returns a string representation of the Position.
     * @return A string in the format "(row, col)".
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", row, col);
    }
}