package coderetreat.game_of_life;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterates through all positions from min to max in order.
 * 
 * @author Maciej Kolodziej <makolodz@gmail.com>
 * 
 */
public class PositionsIterator implements Iterator<Position> {
    
    private Position current;
    private final Position max;
    private final Position min;

    public PositionsIterator(Position min, Position max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("Min position is greater than max");
        }
        this.min = min;
        this.current = min;
        this.max = max;
    }

    public boolean hasNext() {
        return current != null;
    }
    
    public Position next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        
        Position crnt = current;
        
        if (current.equals(max)) {
            current = null;
            return max;
        } else if (current.getColumn() < max.getColumn()) {
            current = new Position(current.getRow(), current.getColumn() + 1);
        } else {
            current = new Position(current.getRow() + 1, min.getColumn());
        }
        
        return crnt;
    }
    
    public void remove() {
        // TODO Auto-generated method stub
        
    }
    
}
