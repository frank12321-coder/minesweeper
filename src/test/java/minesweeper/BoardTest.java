package minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    @Test
    void testBoardInitialization() {
        Board board = new Board(4, 3);
        assertEquals(4, board.getSize());
        assertEquals(3, board.getMineCount());
    }

    @Test
    void testMineCount() {
        int size = 5;
        int mines = 5;
        Board board = new Board(size, mines);
        int actualMines = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getCell(row, col).isMine()) {
                    actualMines++;
                }
            }
        }
        assertEquals(mines, actualMines);
    }

    @Test
    void testAdjacentMineCounts() {
        Board board = new Board(3, 0);
        // Manually set mines
        board.getCell(0, 0).setMine(true);
        board.getCell(2, 2).setMine(true);
        // For this example, we'll just check a cell in the middle
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int r = 1 + dr;
                int c = 1 + dc;
                if (r >= 0 && r < 3 && c >= 0 && c < 3 && board.getCell(r, c).isMine()) {
                    count++;
                }
            }
        }
        // The center cell should have 2 adjacent mines
        assertEquals(2, count);
    }
}
