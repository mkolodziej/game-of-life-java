package coderetreat.game_of_life;

import java.util.Collection;

import com.google.common.collect.Lists;

public class AdjacencyCalculator {
    
    public Collection<Position> getAdjacentPositions(Position position) {
        // @formatter:off
        return Lists.newArrayList(
                new Position(position.getRow()-1, position.getColumn()),
                new Position(position.getRow()-1, position.getColumn()-1),
                new Position(position.getRow(), position.getColumn()-1),
                new Position(position.getRow()+1, position.getColumn()-1),
                new Position(position.getRow()+1, position.getColumn()),
                new Position(position.getRow()+1, position.getColumn()+1),
                new Position(position.getRow(), position.getColumn()+1),
                new Position(position.getRow()-1, position.getColumn()+1)
                );
        // @formatter:on
    }
    
}
