package minesweeper;

public class BoardRenderDisplay {
    public String render(Board board) {
        StringBuilder sb = new StringBuilder();
        int size = board.getSize();

        // Column headers
        sb.append("  ");
        for (int col = 1; col <= size; col++) {
            sb.append(col).append(" ");
        }
        sb.append("\n");

        // Rows
        for (int row = 0; row < size; row++) {
            char rowLabel = (char) ('A' + row);
            sb.append(rowLabel).append(" ");
            for (int col = 0; col < size; col++) {
                Cell cell = board.getCell(row, col);
                if (!cell.isRevealed()) {
                    sb.append("_");
                } else if (cell.isMine()) {
                    sb.append("*");
                } else {
                    sb.append(cell.getAdjacentMines());
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
