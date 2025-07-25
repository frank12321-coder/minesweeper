package minesweeper;

public class InputParser {
    /**
     * Parses input like "A1" into (row, col) coordinates.
     * @param input the user input string
     * @param boardSize the size of the board
     * @return int array [row, col]
     * @throws IllegalArgumentException if input is invalid
     */
    public int[] parse(String input, int boardSize) {
        if (input == null || input.length() < 2) {
            throw new IllegalArgumentException("Input must be at least 2 characters (e.g., A1)");
        }
        char rowChar = Character.toUpperCase(input.charAt(0));
        if (rowChar < 'A' || rowChar >= 'A' + boardSize) {
            throw new IllegalArgumentException("Row must be between A and " + (char)('A' + boardSize - 1));
        }
        int row = rowChar - 'A';
        String colStr = input.substring(1);
        int col;
        try {
            col = Integer.parseInt(colStr) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Column must be a number");
        }
        if (col < 0 || col >= boardSize) {
            throw new IllegalArgumentException("Column must be between 1 and " + boardSize);
        }
        return new int[] { row, col };
    }
}
