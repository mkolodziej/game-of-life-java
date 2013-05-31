package coderetreat.game_of_life;

import static java.util.Arrays.asList;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Sets;

public class BoardTest {
    
    private LifeCalculator lifeCalculator;
    private AdjacencyCalculator adjacencyCalculator;
    
    @Before
    public void setUp() {
        adjacencyCalculator = mock(AdjacencyCalculator.class);
        lifeCalculator = mock(LifeCalculator.class);
    }

    @Test
    public void shouldEqualWhenHasSameListOfLiveCells() {
        Cell cell1 = new Cell(new Position(0, 0));
        Cell cell2 = new Cell(new Position(1, 1));
        Board board = new Board(asList(cell1, cell2));
        Board board2 = new Board(asList(cell1, cell2));
        
        assertThat(board2).isEqualTo(board);
    }
    
    @Test
    public void shouldNotEqualWhenHasDifferentListOfLiveCells() {
        Cell cell1 = new Cell(new Position(0, 0));
        Cell cell2 = new Cell(new Position(0, 1));
        Cell cell3 = new Cell(new Position(10, 0));
        Cell cell4 = new Cell(new Position(10, 1));
        Board board = new Board(asList(cell1, cell2));
        Board board2 = new Board(asList(cell3, cell4));
        
        assertThat(board2).isNotEqualTo(board);
    }
    
    @Test
    public void shouldEnforceUniquenessOfLiveCells() {
        Position position = new Position(0, 0);
        Cell cell1 = new Cell(position);
        Cell cell2 = new Cell(position);
        
        try {
            new Board(asList(cell1, cell2));
            fail("IllegalArgumetnException should be thrown because cells have same positions");
        } catch (IllegalArgumentException ex) {
            assertThat(ex).hasMessage("There's more than one cell at position: " + position.toString());
        }
        
    }
    
    @Test
    public void shouldNotChangeBoardAfterZeroIterations() {
        Board initialBoard = new Board();
        
        Board resultBoard = initialBoard.calculateNextState();
        
        assertEquals(initialBoard, resultBoard);
    }
    
    @Test
    public void shouldReturnBoardWithSameLiveCellsForInitialBoardWithImmutableConfiguration() {
        Cell cell1 = new Cell(new Position(0, 0));
        Cell cell2 = new Cell(new Position(1, 0));
        Cell cell3 = new Cell(new Position(0, 1));
        Cell cell4 = new Cell(new Position(1, 1));
        Board initialBoard = new Board(asList(cell1, cell2, cell3, cell4));
        
        Board resultBoard = initialBoard.calculateNextState();
        
        assertEquals(initialBoard, resultBoard);
    }
    
    @Test
    public void shouldReturnEmptyBoardAfterOneIterationForInitialBoardWithSingleCell() {
        Board initialBoard = new Board(asList(new Cell(new Position(0, 0))));
        
        Board resultBoard = initialBoard.calculateNextState();
        
        assertEquals(0, resultBoard.getLiveCells().size());
    }
    
    @Test
    public void shouldCallAdjacencyCalculatorForLiveCellsPositions() {
        Position position = new Position(0, 0);
        Board initialBoard = new Board(asList(new Cell(position)), adjacencyCalculator, lifeCalculator);
        
        initialBoard.calculateNextState();
        
        verify(adjacencyCalculator, times(1)).getAdjacentPositions(same(position));
    }
    
    @Test
    public void shouldCallLifeCalculatorForLiveCellsPositionsAndAdjacentPositions() {
        Position position = new Position(0, 0);
        Position expectedAdjacentPosition = new Position(1, 1);
        Board initialBoard = new Board(asList(new Cell(position)), adjacencyCalculator, lifeCalculator);
        when(adjacencyCalculator.getAdjacentPositions(position)).thenReturn(asList(expectedAdjacentPosition));
        
        initialBoard.calculateNextState();
        
        verify(lifeCalculator, times(1)).getCellsSurvivingAndBeingBorn(same(initialBoard), Mockito.eq(Sets.newHashSet(position, expectedAdjacentPosition)));
        Mockito.verifyNoMoreInteractions(lifeCalculator);
    }
    
    @Test
    public void shouldReturnEmptyStringFromToStringForEmptyBoard() {
        assertThat(new Board().toString()).isEqualTo("<>");
    }
    
    @Test
    public void shouldReturnBoardRepresentationFromToString() {
        Cell cell1 = new Cell(new Position(0, 0));
        Cell cell2 = new Cell(new Position(1, 0));
        Cell cell3 = new Cell(new Position(0, 1));
        Cell cell4 = new Cell(new Position(1, 1));
        
        Board board = new Board(asList(cell1, cell2, cell3, cell4));
        
        assertThat(board.toString()).isEqualTo("\nx x \nx x ");
    }
    
    @Test
    public void shouldReturnCorrectBoardRepresentationForHorizontalRowOfCells() {
        Cell cell4 = new Cell(new Position(1, -1));
        Cell cell5 = new Cell(new Position(1, 0));
        Cell cell6 = new Cell(new Position(1, 1));

        Board board = new Board(asList(cell4, cell5, cell6));
        assertThat(board.toString()).isEqualTo("\nx x x ");
    }
    
    @Test
    public void shouldReturnTrueFromIsAliveIfTheresALiveCellAtPosition() {
        Cell cell = new Cell(new Position(0, 0));
        Board board = new Board(asList(cell));
        
        assertThat(board.isAlive(new Position(0, 0))).isTrue();
    }
    
    @Test
    public void shouldReturnFalseFromIsAliveIfTheresNoLiveCellAtPosition() {
        Cell cell = new Cell(new Position(0, 0));
        Board board = new Board(asList(cell));
        
        assertThat(board.isAlive(new Position(1, 1))).isFalse();
    }

    @Test
    public void shouldReturnLiveNeighboursCountForPosition() {
        Cell cell1 = new Cell(new Position(0, 0));
        Cell cell2 = new Cell(new Position(1, 0));
        
        Board board = new Board(asList(cell1, cell2));
        
        assertThat(board.getLiveNeighboursCount(new Position(0, 1))).isEqualTo(2);
    }

}
