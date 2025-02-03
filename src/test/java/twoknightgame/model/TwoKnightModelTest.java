package twoknightgame.model;

import game.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TwoKnightModelTest {

    private TwoKnightModel model;

    @BeforeEach
    void setUp() {
        model = new TwoKnightModel();

        model.getBoard()[0][0].set(Square.WHITEKNIGHT);
        model.getBoard()[7][7].set(Square.BLACKKNIGHT);

        model.addToForbiddenpositions(new Position(0,0));
        model.addToForbiddenpositions(new Position(7,7));
        model.setMoveMade(true);
    }

    @Test
    void testInitialPlayer() {
        assertEquals(State.Player.PLAYER_1, model.getCurrentPlayer());
    }

    @Test
    void testAddToForbiddenPositions() {
        Position position = new Position(1, 1);
        model.addToForbiddenpositions(position);
        assertTrue(model.getForbiddenpositions().contains(position));
    }

    @Test
    void testIsLegalToMoveFrom() {
        Position position = new Position(0, 0);
        assertTrue(model.isLegalToMoveFrom(position));
        model.addToForbiddenpositions(new Position(1,2));
        model.addToForbiddenpositions(new Position(2,1));
        assertFalse(model.isLegalToMoveFrom(position));

        Position position2 = new Position(7, 7);
        assertTrue(model.isLegalToMoveFrom(position2));
        model.addToForbiddenpositions(new Position(6,5));
        model.addToForbiddenpositions(new Position(5,6));
        assertFalse(model.isLegalToMoveFrom(position2));
    }

    @Test
    void testIsLegalMove() {
        Position from1 = new Position(0, 0);
        Position to1 = new Position(2, 1);
        assertTrue(model.isLegalMove(from1, to1));

        Position from2 = new Position(2, 1);
        Position to2 = new Position(0, 0);
        assertFalse(model.isLegalMove(from2, to2));


        Position from3 = new Position(7, 7);
        Position to3 = new Position(5, 6);
        assertTrue(model.isLegalMove(from3, to3));

        Position from4 = new Position(5, 6);
        Position to4 = new Position(7, 7);
        assertFalse(model.isLegalMove(from4, to4));
    }

    @Test
    void testMakeMove() {
        Position from = new Position(0, 0);
        Position to = new Position(2, 1);
        model.makeMove(from, to);
        assertEquals(Square.WHITEKNIGHT, model.getSquare(to));

        Position from2 = new Position(7, 7);
        Position to2 = new Position(5, 6);
        model.makeMove(from2, to2);
        assertEquals(Square.BLACKKNIGHT, model.getSquare(to2));
    }

    @Test
    void testSetSquare() {
        Position position = new Position(1, 1);
        model.setSquare(position, Square.WHITEKNIGHT);
        assertEquals(Square.WHITEKNIGHT, model.getSquare(position));

        Position position2 = new Position(2, 2);
        model.setSquare(position2, Square.BLACKKNIGHT);
        assertEquals(Square.BLACKKNIGHT, model.getSquare(position2));

        Position position3 = new Position(2, 2);
        model.setSquare(position3, Square.EMPTY);
        assertEquals(Square.EMPTY, model.getSquare(position3));


    }

    @Test
    void testGetNextPlayer() {
        assertNotEquals(model.getCurrentPlayer(), model.getNextPlayer());
    }

    @Test
    void testIsGameOver() {
        assertFalse(model.isGameOver());
    }

    @Test
    void testGetStatus() {
        assertEquals(State.Status.IN_PROGRESS, model.getStatus());
    }

    @Test
    void testIsWinner() {
        assertFalse(model.isWinner(State.Player.PLAYER_1));
        assertFalse(model.isWinner(State.Player.PLAYER_2));

    }

    @Test
    void testSquareProperty() {
        Position position = new Position(0, 0);
        assertEquals(Square.WHITEKNIGHT, model.squareProperty(position.row(), position.col()).get());

        Position position2 = new Position(1,1);
        assertEquals(Square.EMPTY, model.squareProperty(position2.row(), position2.col()).get());

        Position position3 = new Position(7,7);
        assertEquals(Square.BLACKKNIGHT, model.squareProperty(position3.row(), position3.col()).get());
    }

    @Test
    void testGetSquare() {
        Position position = new Position(0, 0);
        assertEquals(Square.WHITEKNIGHT, model.getSquare(position));

        Position position2 = new Position(7, 7);
        assertEquals(Square.BLACKKNIGHT, model.getSquare(position2));

        Position position3 = new Position(1, 1);
        assertEquals(Square.EMPTY, model.getSquare(position3));
    }

    @Test
    void testToString() {
        String expected =
                "  a b c d e f g h\n" +
                "8 W _ _ _ _ _ _ _ 8\n" +
                "7 _ _ _ _ _ _ _ _ 7\n" +
                "6 _ _ _ _ _ _ _ _ 6\n" +
                "5 _ _ _ _ _ _ _ _ 5\n" +
                "4 _ _ _ _ _ _ _ _ 4\n" +
                "3 _ _ _ _ _ _ _ _ 3\n" +
                "2 _ _ _ _ _ _ _ _ 2\n" +
                "1 _ _ _ _ _ _ _ B 1\n" +
                "  a b c d e f g h\n";

        TwoKnightModel model = new TwoKnightModel();
        assertEquals(expected, model.toString());

        String expected2 =
                        "  a b c d e f g h\n" +
                        "8 B _ _ _ _ _ _ _ 8\n" +
                        "7 _ _ _ _ _ _ _ _ 7\n" +
                        "6 _ _ _ _ _ _ _ _ 6\n" +
                        "5 _ _ _ _ _ _ _ _ 5\n" +
                        "4 _ _ _ _ _ _ _ _ 4\n" +
                        "3 _ _ _ _ _ _ _ _ 3\n" +
                        "2 _ _ _ _ _ _ _ _ 2\n" +
                        "1 _ _ _ _ _ _ _ W 1\n" +
                        "  a b c d e f g h\n";

        assertNotEquals(expected2, model.toString());
    }

}


