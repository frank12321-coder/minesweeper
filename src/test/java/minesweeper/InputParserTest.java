package minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InputParserTest {
    @Test
    void testValidInput() {
        InputParser parser = new InputParser();
        int[] coords = parser.parse("A1", 4);
        assertArrayEquals(new int[]{0, 0}, coords);
        coords = parser.parse("D4", 4);
        assertArrayEquals(new int[]{3, 3}, coords);
        coords = parser.parse("b2", 4);
        assertArrayEquals(new int[]{1, 1}, coords);
    }

    @Test
    void testInvalidRow() {
        InputParser parser = new InputParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse("Z1", 4));
    }

    @Test
    void testInvalidColumn() {
        InputParser parser = new InputParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse("A0", 4));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("A5", 4));
    }

    @Test
    void testInvalidFormat() {
        InputParser parser = new InputParser();
        assertThrows(IllegalArgumentException.class, () -> parser.parse("1A", 4));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("A", 4));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("", 4));
        assertThrows(IllegalArgumentException.class, () -> parser.parse(null, 4));
    }
}
