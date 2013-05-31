package coderetreat.game_of_life;

import com.google.common.base.Objects;

public class Position implements Comparable<Position> {
    
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.row, this.column);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        Position other = (Position) obj;
        return Objects.equal(this.row, other.row) && Objects.equal(this.column, other.column);
    }

    public int compareTo(Position position) {
        if (this.row < position.row) {
            return -1;
        } else if (this.row == position.row && this.column < position.column) {
            return -1;
        } else if (this.row > position.row) {
            return 1;
        } else if (this.row == position.row && this.column > position.column) {
            return 1;
        }
        return 0;
    }

}
