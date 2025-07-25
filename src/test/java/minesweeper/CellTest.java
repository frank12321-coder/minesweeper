package minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {
    @Test
    void testMineStatus() {
        Cell cell = new Cell();
        assertFalse(cell.isMine());
        cell.setMine(true);
        assertTrue(cell.isMine());
    }

    @Test
    void testRevealedStatus() {
        Cell cell = new Cell();
        assertFalse(cell.isRevealed());
        cell.setRevealed(true);
        assertTrue(cell.isRevealed());
    }

    @Test
    void testAdjacentMines() {
        Cell cell = new Cell();
        assertEquals(0, cell.getAdjacentMines());
        cell.setAdjacentMines(3);
        assertEquals(3, cell.getAdjacentMines());
    }
}
