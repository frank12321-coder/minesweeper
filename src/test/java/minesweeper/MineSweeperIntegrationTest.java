package minesweeper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MineSweeperIntegrationTest {

    @Test
    void testWinScenario() {
        // Setup a 2x2 board with 1 mine at (0,0)
        Board board = new Board(2, 0);
        board.getCell(0, 0).setMine(true);
        board.getCell(0, 1).setMine(false);
        board.getCell(1, 0).setMine(false);
        board.getCell(1, 1).setMine(false);
        // Manually set adjacent mine counts
        board.getCell(0, 1).setAdjacentMines(1);
        board.getCell(1, 0).setAdjacentMines(1);
        board.getCell(1, 1).setAdjacentMines(1);

        // Simulate uncovering all non-mine cells
        board.getCell(0, 1).setRevealed(true);
        board.getCell(1, 0).setRevealed(true);
        board.getCell(1, 1).setRevealed(true);

        // Check win condition
        boolean allNonMinesRevealed = true;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                if (!board.getCell(row, col).isMine() && !board.getCell(row, col).isRevealed()) {
                    allNonMinesRevealed = false;
                }
            }
        }
        assertTrue(allNonMinesRevealed, "All non-mine cells should be revealed for a win.");
    }

    @Test
    void testLoseScenario() {
        // Setup a 2x2 board with 1 mine at (0,0)
        Board board = new Board(2, 0);
        board.getCell(0, 0).setMine(true);

        // Simulate uncovering the mine
        board.getCell(0, 0).setRevealed(true);

        // Check lose condition
        assertTrue(board.getCell(0, 0).isMine() && board.getCell(0, 0).isRevealed(), "Revealing a mine should result in a loss.");
    }

    @Test
    void testInputParserIntegration() {
        InputParser parser = new InputParser();
        int[] coords = parser.parse("B2", 3);
        assertArrayEquals(new int[]{1, 1}, coords);
    }
} 