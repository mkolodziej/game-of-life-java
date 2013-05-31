package coderetreat.game_of_life;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.Lists;

public class LifeCalculator {
    
    private boolean shouldLive(Board board, Position position) {
        int neighboursCount = board.getLiveNeighboursCount(position);
        return board.isAlive(position) && neighboursCount == 2 || neighboursCount == 3;
    }

    public Collection<Cell> getCellsSurvivingAndBeingBorn(Board board, Set<Position> positionsToCheck) {
        Collection<Cell> newLiveCells = Lists.newArrayList();
        for (Position position : positionsToCheck) {
            if (shouldLive(board, position))
                newLiveCells.add(new Cell(position));
        }
        return newLiveCells;
    }
    
}
