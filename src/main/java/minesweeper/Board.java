package minesweeper;

import java.util.Random;

public class Board {
    private final int size;
    private final int mineCount;
    private final Cell[][] grid;
    private final Random random;

    public Board(int size, int mineCount) {
        this.size = size;
        this.mineCount = mineCount;
        this.grid = new Cell[size][size];
        this.random = new Random();
        initializeBoard();
        placeMines();
        calculateAdjacents();
    }

    private void initializeBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Cell();
            }
        }
    }

    private void placeMines() {
        int placed = 0;
        while (placed < mineCount) {
            int row = random.nextInt(size);
            int col = random.nextInt(size);
            if (!grid[row][col].isMine()) {
                grid[row][col].setMine(true);
                placed++;
            }
        }
    }

    private void calculateAdjacents() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!grid[row][col].isMine()) {
                    grid[row][col].setAdjacentMines(countAdjacentMines(row, col));
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int r = row + dr;
                int c = col + dc;
                if (r >= 0 && r < size && c >= 0 && c < size && grid[r][c].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }

    public int getMineCount() {
        return mineCount;
    }

    // Uncover logic and flood fill for zero-adjacent-mine cells
    public void uncover(int row, int col) {
        // To be implemented
    }

    // Additional methods for game logic can be added here
} 