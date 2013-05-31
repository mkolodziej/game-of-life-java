package coderetreat.game_of_life;

import java.util.Collection;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class AdjacencyCalculatorTest {
    
    @Test
    public void shouldGetAdjactentPositionsOfAGivenPosition() {
        AdjacencyCalculator adjacencyCalculator = new AdjacencyCalculator();
        Position position = new Position(0, 0);

        Collection<Position> adjacentPositions = adjacencyCalculator.getAdjacentPositions(position);
        
        // @formatter:off
        Assertions.assertThat(adjacentPositions).containsOnly(
                new Position(-1, 0),
                new Position(-1, -1),
                new Position(0, -1),
                new Position(1, -1),
                new Position(1, 0),
                new Position(1, 1),
                new Position(0, 1),
                new Position(-1, 1)
                );
        // @formatter:on
    }
    
}
