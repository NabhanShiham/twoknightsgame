/**
 * This package contains the model classes for the Two Knight game.
 */
package twoknightgame.model;

import game.TwoPhaseMoveState;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the model for the Two Knight game.
 */
public class TwoKnightModel implements TwoPhaseMoveState<Position> {
    /**
     * Represents the number of rows and columns that will be generated for the chess board.
     */
    public static final int BOARD_SIZE = 8;
    @Getter
    private final ReadOnlyObjectWrapper<Square>[][] board = new ReadOnlyObjectWrapper[BOARD_SIZE][BOARD_SIZE];
    @Getter
    private Player currentPlayer = Player.PLAYER_1;
    @Getter
    private final Set<Position> forbiddenpositions = new HashSet<>();

    private Position whiteKnightPos = new Position(0,0);
    private Position blackKnightPos = new Position(7,7);
    @Setter
    private boolean isMoveMade = false;

    /**
     * Initializes the TwoKnightModel with the initial positions of the knights.
     */
    public TwoKnightModel(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new ReadOnlyObjectWrapper<Square>(Square.EMPTY);
            }
        }

        board[0][0] = new ReadOnlyObjectWrapper<Square>(Square.WHITEKNIGHT);
        board[7][7] = new ReadOnlyObjectWrapper<Square>(Square.BLACKKNIGHT);

        addToForbiddenpositions(whiteKnightPos);
        addToForbiddenpositions(blackKnightPos);
    }

    /**
     * Adds a position to the set of forbidden positions.
     * @param position The position to be added to the forbidden positions.
     */
    public void addToForbiddenpositions(Position position) {
        forbiddenpositions.add(position);
    }

    /**
     * Checks if it is legal to move from the given position.
     * @param from The position to check.
     * @return true if it is legal to move from the given position, false otherwise.
     */
    @Override
    public boolean isLegalToMoveFrom(Position from) {
        int[] rowChanges = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] colChanges = {1, 2, 2, 1, -1, -2, -2, -1};

        for (int i = 0; i < 8; i++) {
            int testRow = from.row() + rowChanges[i];
            int testCol = from.col() + colChanges[i];

            if (testRow >= 0 && testRow < BOARD_SIZE && testCol >= 0 && testCol < BOARD_SIZE) {
                Position to = new Position(testRow, testCol);

                if(isLegalMove(from, to)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the move from the given position to the target position is legal.
     * @param from The starting position.
     * @param to The target position.
     * @return true if the move is legal, false otherwise.
     */
    @Override
    public boolean isLegalMove(Position from, Position to) {
        if (forbiddenpositions.contains(to)){
            return false;
        }
        int rowDiff = Math.abs(from.row() - to.row());
        int colDiff = Math.abs(from.col() - to.col());

        return ((rowDiff == 2) && (colDiff == 1)) || ((rowDiff == 1) && (colDiff == 2));
    }

    /**
     * Makes a move from the given position to the target position.
     * @param from The starting position.
     * @param to The target position.
     */
    @Override
    public void makeMove(Position from, Position to) {
        setSquare(to, getSquare(from));
        setSquare(from, Square.EMPTY);
        if (currentPlayer == Player.PLAYER_1) {
            whiteKnightPos = to;
        } else {
            blackKnightPos = to;
        }
        addToForbiddenpositions(to);
        isMoveMade = true;
        currentPlayer = getNextPlayer();
    }
    /**
     * Sets the given square at the given position.
     * @param p The position to set the square.
     * @param square The square to be set.
     */
    public void setSquare(Position p, Square square) {
        board[p.row()][p.col()].set(square);
    }

    /**
     * Gets the next player.
     * @return The next player.
     */
    public Player getNextPlayer() {
        if (isMoveMade){
            if (currentPlayer == Player.PLAYER_1){
                currentPlayer = Player.PLAYER_2;
            } else {
                currentPlayer = Player.PLAYER_1;
            }
            isMoveMade = false;
        }
        return currentPlayer;
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        Position currentPlayerPosition = (currentPlayer == Player.PLAYER_1) ? whiteKnightPos : blackKnightPos;
        return !isLegalToMoveFrom(currentPlayerPosition);
    }

    /**
     * Gets the status of the game.
     * @return The status of the game.
     */
    @Override
    public Status getStatus() {
        if (!isGameOver()){
            return Status.IN_PROGRESS;
        }
        return (currentPlayer == Player.PLAYER_1) ? Status.PLAYER_2_WINS : Status.PLAYER_1_WINS;
    }

    /**
     * Checks if the given player is the winner.
     * @param player The player to check.
     * @return true if the given player is the winner, false otherwise.
     */
    @Override
    public boolean isWinner(Player player) {
        return TwoPhaseMoveState.super.isWinner(player);
    }

    /**
     * Gets the square property at the given position.
     * @param i The row of the position.
     * @param j The column of the position.
     * @return The square property at the given position.
     */
    public ReadOnlyObjectProperty<Square> squareProperty(int i, int j) {
        return board[i][j].getReadOnlyProperty();
    }

    /**
     * Gets the square at the given position.
     * @param p The position to get the square.
     * @return The square at the given position.
     */
    public Square getSquare(Position p) {
        return board[p.row()][p.col()].get();
    }

    /**
     * Returns a string representation of the TwoKnightModel.
     * @return A string representation of the TwoKnightModel.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  a b c d e f g h\n");
        for (int i = 0; i < BOARD_SIZE; i++) {
            stringBuilder.append((BOARD_SIZE - i)).append(" ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                Position p = new Position(i, j);
                Square square = board[i][j].get();
                if (forbiddenpositions.contains(p) && (square == Square.EMPTY)) {
                    stringBuilder.append("X ");
                } else if (square == Square.EMPTY) {
                    stringBuilder.append("_ ");
                } else if (square == Square.WHITEKNIGHT) {
                    stringBuilder.append("W ");
                } else if (square == Square.BLACKKNIGHT) {
                    stringBuilder.append("B ");
                }
            }
            stringBuilder.append((BOARD_SIZE - i)).append("\n");
        }
        stringBuilder.append("  a b c d e f g h\n");
        return stringBuilder.toString();
    }
}
