package coderetreat.game_of_life;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class BoardPositionsHelperTest {
    

    @Test
    public void shouldReturnMaximumPositionForNonEmptyBoard() {
        Board board = new Board(Arrays.asList(new Cell(0, 1), new Cell(1, 0)));
        
        Position maximumPosition = new BoardPositionsHelper().getMaximumPosition(board);
        
        Assertions.assertThat(maximumPosition).isEqualTo(new Position(1, 1));
    }
    
    @Test
    public void shouldReturnMinimumPositionForNonEmptyBoard() {
        Board board = new Board(Arrays.asList(new Cell(0, 1), new Cell(1, 0)));
        
        Position minimumPosition = new BoardPositionsHelper().getMinimumPosition(board);
        
        assertThat(minimumPosition).isEqualTo(new Position(0, 0));
    }
    
    @Test
    public void shouldReturnEqualPositionsAsMinimumAndMaximumForSingleCellBoard() {
        Board board = new Board(Arrays.asList(new Cell(1, 0)));
        
        Position minimumPosition = new BoardPositionsHelper().getMinimumPosition(board);
        Position maximumPosition = new BoardPositionsHelper().getMaximumPosition(board);
        
        assertThat(maximumPosition).isEqualTo(minimumPosition);
    }
    
    @Test
    public void shouldReturnNullMinimumPositionForNonEmptyBoard() {
        Board board = new Board();
        
        assertThat(new BoardPositionsHelper().getMinimumPosition(board)).isNull();
    }
    
    @Test
    public void shouldReturnNullMaximumPositionForNonEmptyBoard() {
        Board board = new Board();
        
        assertThat(new BoardPositionsHelper().getMaximumPosition(board)).isNull();
    }

}
