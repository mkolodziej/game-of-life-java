package coderetreat.game_of_life;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class CellTest {
    
    @Test
    public void shouldEqualWhenPositionsAreEqual() {
        Position position = new Position(0, 0);
        Cell cell1 = new Cell(position);
        Cell cell2 = new Cell(position);
        
        assertThat(cell1).isEqualTo(cell2);
    }
    
    @Test
    public void shouldNotEqualWhenPositionsAreDifferent() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(1, 1);
        Cell cell1 = new Cell(position1);
        Cell cell2 = new Cell(position2);
        
        assertThat(position1).isNotEqualTo(position2);
        assertThat(cell1).isNotEqualTo(cell2);
    }

}
