package twoknightgame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void testNumberOfConstants() {
        assertEquals(3, Square.values().length);
    }

    @Test
    void testConstantNames() {
        assertEquals("WHITEKNIGHT", Square.WHITEKNIGHT.name());
        assertEquals("BLACKKNIGHT", Square.BLACKKNIGHT.name());
        assertEquals("EMPTY", Square.EMPTY.name());
    }
}