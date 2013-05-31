package coderetreat.game_of_life;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

public class PositionsIteratorTest {
    
    @Test
    public void shouldThrowExceptionIfMinPositionIsGreaterThanMax() {
        Position min = new Position(1, 1);
        Position max = new Position(0, 0);
        
        try {
            new PositionsIterator(min, max);
            Assert.fail("Should throw IllegalArgument exception");
        } catch (IllegalArgumentException ex) {
            assertThat(ex).hasMessage("Min position is greater than max");
        }
    }
    
    @Test
    public void shouldReturnFirstPositionOnTheFirstCallToNext() {
        Position min = new Position(0, 0);
        Position max = new Position(1, 1);
        
        Position next = new PositionsIterator(min, max).next();
        
        assertThat(next).isEqualTo(new Position(0, 0));
    }
    
    @Test
    public void shouldReturnNextPositionFromTheSameRowIfAvailable() {
        Position min = new Position(0, 0);
        Position max = new Position(1, 1);
        
        PositionsIterator positionsIterator = new PositionsIterator(min, max);
        positionsIterator.next();
        Position next = positionsIterator.next();
        
        assertThat(next).isEqualTo(new Position(0, 1));
    }
    
    @Test
    public void shouldReturnFirstPositionFromTheNextRowIfCurrentIsLastInRow() {
        Position min = new Position(-1, -1);
        Position max = new Position(0, 0);
        
        PositionsIterator positionsIterator = new PositionsIterator(min, max);
        positionsIterator.next();
        positionsIterator.next();
        Position next = positionsIterator.next();
        
        assertThat(next).isEqualTo(new Position(0, -1));
    }
    
    @Test
    public void shouldThrowExceptionIfNoNextElement() {
        Position min = new Position(1, 1);
        Position max = new Position(1, 1);
        
        PositionsIterator positionsIterator = new PositionsIterator(min, max);
        positionsIterator.next();
        try {
            positionsIterator.next();
            fail("Should throw exception");
        } catch (NoSuchElementException ex) {
        }
        
    }
    
    @Test
    public void shouldReturnTrueFromHasNextIfNotAtLastElement() {
        Position min = new Position(0, 0);
        Position max = new Position(1, 1);
        
        PositionsIterator positionsIterator = new PositionsIterator(min, max);
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
    }
    
    @Test
    public void shouldReturnFalseFromHasNextIfAtLastElement() {
        Position min = new Position(1, 1);
        Position max = new Position(1, 1);
        
        PositionsIterator positionsIterator = new PositionsIterator(min, max);
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isFalse();
    }

}
