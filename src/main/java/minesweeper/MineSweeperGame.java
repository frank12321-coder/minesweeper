package minesweeper;

import java.util.Scanner;

public class MineSweeperGame {
    private Board board;
    private BoardRenderDisplay renderer;
    private InputParser parser;
    private Scanner scanner;
    private boolean gameOver;
    private boolean win;

    public MineSweeperGame() {
        this.renderer = new BoardRenderDisplay();
        this.parser = new InputParser();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to Minesweeper!\n");
        int size = promptGridSize();
        int mineCount = promptMineCount(size);
        this.board = new Board(size, mineCount);
        this.gameOver = false;
        this.win = false;
        gameLoop();
    }

    private int promptGridSize() {
        int size = 0;
        while (size < 2 || size > 26) {
            System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
            String input = scanner.nextLine();
            try {
                size = Integer.parseInt(input);
                if (size < 2 || size > 26) {
                    System.out.println("Grid size must be between 2 and 26.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return size;
    }

    private int promptMineCount(int size) {
        int maxMines = (int) Math.floor(size * size * 0.35);
        int mineCount = 0;
        while (mineCount < 1 || mineCount > maxMines) {
            System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
            String input = scanner.nextLine();
            try {
                mineCount = Integer.parseInt(input);
                if (mineCount < 1 || mineCount > maxMines) {
                    System.out.println("Mine count must be between 1 and " + maxMines + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
        return mineCount;
    }

    private void gameLoop() {
        while (!gameOver && !win) {
            System.out.println("\nHere is your minefield:");
            System.out.print(renderer.render(board));
            int[] coords = null;
            while (coords == null) {
                System.out.print("Select a square to reveal (e.g. A1): ");
                String input = scanner.nextLine();
                try {
                    coords = parser.parse(input, board.getSize());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            processMove(coords[0], coords[1]);
            checkWin();
        }
        System.out.println(renderer.render(board));
        if (win) {
            System.out.println("Congratulations, you have won the game!");
        } else {
            System.out.println("Oh no, you detonated a mine! Game over.");
        }
        System.out.println("Press any key to play again...");
        scanner.nextLine();
        start();
    }

    private void processMove(int row, int col) {
        Cell cell = board.getCell(row, col);
        if (cell.isRevealed()) {
            System.out.println("This square is already revealed. Try another.");
            return;
        }
        cell.setRevealed(true);
        if (cell.isMine()) {
            gameOver = true;
        } else {
            System.out.println("This square contains " + cell.getAdjacentMines() + " adjacent mines. ");
            if (cell.getAdjacentMines() == 0) {
                floodFill(row, col);
            }
        }
    }

    // Uncover all adjacent cells recursively if they have 0 adjacent mines
    private void floodFill(int row, int col) {
        int size = board.getSize();
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) continue;
                int r = row + dr;
                int c = col + dc;
                if (r >= 0 && r < size && c >= 0 && c < size) {
                    Cell adj = board.getCell(r, c);
                    if (!adj.isRevealed() && !adj.isMine()) {
                        adj.setRevealed(true);
                        if (adj.getAdjacentMines() == 0) {
                            floodFill(r, c);
                        }
                    }
                }
            }
        }
    }

    private void checkWin() {
        int size = board.getSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Cell cell = board.getCell(row, col);
                if (!cell.isMine() && !cell.isRevealed()) {
                    return;
                }
            }
        }
        win = true;
    }

    public static void main(String[] args) {
        new MineSweeperGame().start();
    }
}
