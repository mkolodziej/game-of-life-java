package coderetreat.game_of_life;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class PositionTest {
    
    @Test
    public void shouldBeEqualIfBothCoordinatesAreEqual() {
        Position position = new Position(1, 1);
        Position position2 = new Position(1, 1);
        
        assertThat(position2).isEqualTo(position);
    }
    
    @Test
    public void shouldNotBeEqualIfBothCoordinatesAreNotEqual() {
        Position position = new Position(1, 1);
        Position position2 = new Position(-1, -1);
        
        assertThat(position2).isNotEqualTo(position);
    }
    
    @Test
    public void shouldNotBeEqualIfOneOfTheCoordinatesAreNotEqual() {
        Position position = new Position(1, -1);
        Position position2 = new Position(1, 1);
        
        assertThat(position2).isNotEqualTo(position);
    }
    
    @Test
    public void shouldReturnZeroFromCompareToIfEqual() {
        Position position = new Position(1, 1);
        Position position2 = new Position(1, 1);
        
        assertThat(position.compareTo(position2)).isZero();
    }
    
    @Test
    public void shouldReturnMinusOneFromCompareToIfXSmallerAndYEqual() {
        Position position = new Position(0, 1);
        Position position2 = new Position(1, 1);
        
        assertThat(position.compareTo(position2)).isEqualTo(-1);
    }
    
    @Test
    public void shouldReturnMinusOneFromCompareToIfYSmallerAndXEqual() {
        Position position = new Position(1, 0);
        Position position2 = new Position(1, 1);
        
        assertThat(position.compareTo(position2)).isEqualTo(-1);
    }
    
    @Test
    public void shouldCorrectlyCompareWhenXSmallerAndYGreater() {
        Position position = new Position(1, 0);
        Position position2 = new Position(0, 1);
        
        assertThat(position.compareTo(position2)).isNotEqualTo(0);
    }
    
    @Test
    public void shouldReturnMinusOneFromCompareToIfBothXandYSmaller() {
        Position position = new Position(0, 0);
        Position position2 = new Position(1, 1);
        
        assertThat(position.compareTo(position2)).isEqualTo(-1);
    }
    
    @Test
    public void shouldReturnOneFromCompareToIfBothXandYLarger() {
        Position position = new Position(1, 1);
        Position position2 = new Position(0, 0);
        
        assertThat(position.compareTo(position2)).isEqualTo(1);
    }
    
    @Test
    public void shouldReturnOneFromCompareToIfXLargerAndYEqual() {
        Position position = new Position(1, 0);
        Position position2 = new Position(0, 0);
        
        assertThat(position.compareTo(position2)).isEqualTo(1);
    }
    
    @Test
    public void shouldReturnOneFromCompareToIfXEqualAndYLarger() {
        Position position = new Position(0, 1);
        Position position2 = new Position(0, 0);
        
        assertThat(position.compareTo(position2)).isEqualTo(1);
    }



}
