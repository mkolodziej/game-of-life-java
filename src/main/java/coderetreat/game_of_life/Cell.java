package coderetreat.game_of_life;

import com.google.common.base.Objects;

public class Cell {
    
    private static final String CELL = "Cell";
    private final Position position;

    public Cell(Position position) {
        this.position = position;
    }
    
    public Cell(int row, int column) {
        this(new Position(row, column));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.position);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Cell other = (Cell) obj;
        return Objects.equal(this.position, other.position);
    }
    
    @Override
    public String toString() {
        return CELL + getPosition().toString();
    }

    public Position getPosition() {
        return this.position;
    }

}
