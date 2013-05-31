package coderetreat.game_of_life;


public class BoardPositionsHelper {
    
    /**
     * Minimum position is one that has the smallest row of all live cells' positions on the board and the smallest
     * column of all live cells' positions on the board (effectively a top-left corner of the piece of the infinite
     * board containing all the live cells).
     * 
     * @param board
     *            board to use
     * 
     * @return minimum position
     */
    public Position getMinimumPosition(Board board) {
        if (board.getLiveCells().isEmpty())
            return null;

        int minRow = Integer.MAX_VALUE, minColumn = Integer.MAX_VALUE;
        for (Cell cell : board.getLiveCells()) {
            Position position = cell.getPosition();
            if (position.getRow() < minRow)
                minRow = position.getRow();
            if (position.getColumn() < minColumn)
                minColumn = position.getColumn();
        }
        return new Position(minRow, minColumn);
    }
    
    /**
     * Maximum position is one that has the largest row of all live cells' positions on the board and the largest column
     * of all live cells' positions on the board (effectively a bottom-right corner of the piece of the infinite board
     * containing all the live cells)
     * 
     * @param board
     *            board to use
     * @return maximum position
     */
    public Position getMaximumPosition(Board board) {
        if (board.getLiveCells().isEmpty())
            return null;

        int maxRow = Integer.MIN_VALUE, maxColumn = Integer.MIN_VALUE;
        for (Cell cell : board.getLiveCells()) {
            Position position = cell.getPosition();
            if (position.getRow() > maxRow)
                maxRow = position.getRow();
            if (position.getColumn() > maxColumn)
                maxColumn = position.getColumn();
        }
        return new Position(maxRow, maxColumn);
    }
    
}
