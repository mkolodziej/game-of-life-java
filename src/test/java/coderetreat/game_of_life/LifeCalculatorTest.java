package coderetreat.game_of_life;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Sets;

public class LifeCalculatorTest {
    
    @Test
    public void shouldGetNeighboursCountFromTheBoard() {
        Position position = new Position(0, 0);
        Board board = Mockito.mock(Board.class);
        when(board.getLiveNeighboursCount(position)).thenReturn(3);
        LifeCalculator lifeCalculator = new LifeCalculator();
        
        Collection<Cell> liveCells = lifeCalculator.getCellsSurvivingAndBeingBorn(board, Sets.newHashSet(position));

        verify(board, times(1)).getLiveNeighboursCount(position);
        verify(board, times(1)).isAlive(position);
        verifyNoMoreInteractions(board);
        assertThat(liveCells).contains(new Cell(position));
    }
    
}
