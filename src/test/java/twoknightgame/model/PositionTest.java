package twoknightgame.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void testEquals() {
        Position position1 = new Position(1, 1);
        Position position2 = new Position(1, 1);
        Position position3 = new Position(2, 2);

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
    }

    @Test
    void testHashCode() {
        Position position1 = new Position(1, 1);
        Position position2 = new Position(1, 1);
        Position position3 = new Position(2, 2);

        assertEquals(position1.hashCode(), position2.hashCode());
        assertNotEquals(position1.hashCode(), position3.hashCode());
    }
}