package coderetreat.game_of_life;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GameTest {
    
    
    
    private Board mockBoard;
    
    @Before
    public void setUp() {
        mockBoard = Mockito.mock(Board.class);
    }

    @Test
    public void shouldSimulateByCalculatingNextBoardStateSetNumberOfTimes() {
        int sampleIterationsCount = 5;
        when(mockBoard.calculateNextState()).thenReturn(mockBoard);
        
        new Game().simulate(sampleIterationsCount, mockBoard);
        
        verify(mockBoard, Mockito.times(sampleIterationsCount)).calculateNextState();
    }
    
    @Test
    public void shouldCallListenerForEachBoardState() {
        int sampleIterationsCount = 5;
        when(mockBoard.calculateNextState()).thenReturn(mockBoard);
        SimulationListener simulationListener = Mockito.mock(SimulationListener.class);
        
        new Game().simulate(sampleIterationsCount, mockBoard, simulationListener);
        
        verify(mockBoard, times(sampleIterationsCount)).calculateNextState();
        verify(simulationListener, times(sampleIterationsCount)).newBoardState(Mockito.anyInt(), Mockito.same(mockBoard));
        
    }

}
