package coderetreat.game_of_life;

import static com.google.common.collect.Collections2.filter;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Stores live cells, can calculate its next state. Is immutable. Can print itself out.
 * 
 * @author Maciej Kolodziej <makolodz@gmail.com>
 * 
 */
public class Board {
    private final SortedMap<Position, Cell> liveCellsMap;
    private AdjacencyCalculator adjacencyCalculator = new AdjacencyCalculator();
    private LifeCalculator lifeCalculator = new LifeCalculator();
    private BoardPositionsHelper boardPositionsHelper = new BoardPositionsHelper();
    
    public Board() {
        this(Collections.<Cell> emptyList());
    }

    public Board(Collection<Cell> liveCells) {
        liveCellsMap = Collections.unmodifiableSortedMap(buildCellsMap(liveCells));
    }

    public Board(List<Cell> liveCells, AdjacencyCalculator adjacencyCalculator) {
        this(liveCells);
        this.adjacencyCalculator = adjacencyCalculator;
    }

    public Board(List<Cell> liveCells, AdjacencyCalculator adjacencyCalculator, LifeCalculator lifeCalculator) {
        this(liveCells, adjacencyCalculator);
        this.lifeCalculator = lifeCalculator;
    }

    private SortedMap<Position, Cell> buildCellsMap(Collection<Cell> liveCells) {
        SortedMap<Position, Cell> liveCellsMap = Maps.newTreeMap();
        for (Cell cell : liveCells) {
            Position position = cell.getPosition();
            if (liveCellsMap.get(position) != null) {
                throw new IllegalArgumentException("There's more than one cell at position: " + position.toString());
            } else {
                liveCellsMap.put(position, cell);
            }
        }
        
        return liveCellsMap;
    }
    
    public Collection<Cell> getLiveCells() {
        return Lists.newArrayList(liveCellsMap.values());
    }
    
    public Board calculateNextState() {
        Set<Position> liveCellsPositionsAndAdjacent = getLiveCellsPositionsAndAdjacent();
        Collection<Cell> liveCells = lifeCalculator.getCellsSurvivingAndBeingBorn(this, liveCellsPositionsAndAdjacent);
        return new Board(liveCells);
    }

    private Set<Position> getLiveCellsPositionsAndAdjacent() {
        Set<Position> cellsPositionsAndAdjacent = Sets.newHashSet();
        for (Cell cell : getLiveCells()) {
            cellsPositionsAndAdjacent.add(cell.getPosition());
            cellsPositionsAndAdjacent.addAll(adjacencyCalculator.getAdjacentPositions(cell.getPosition()));
        }
        return cellsPositionsAndAdjacent;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.liveCellsMap);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        
        Board other = (Board) obj;
        return Objects.equal(this.liveCellsMap, other.liveCellsMap);
    }
    
    @Override
    public String toString() {
        if (liveCellsMap.isEmpty()) {
            return "<>";
        }

        final String LIVE = new String(Character.toChars(0x25CF));
        final String EMPTY = new String(Character.toChars(0x25CB));
        StringBuilder builder = new StringBuilder();
        Position firstPosition = boardPositionsHelper.getMinimumPosition(this);
        Position lastPosition = boardPositionsHelper.getMaximumPosition(this);
        Iterator<Position> positionsIterator = new PositionsIterator(firstPosition, lastPosition);
        while (positionsIterator.hasNext()) {
            Position position = positionsIterator.next();
            if (position.getColumn() == firstPosition.getColumn())
                builder.append("\n");
            builder.append(liveCellsMap.containsKey(position) ? LIVE + " " : EMPTY + " ");
        }
        return builder.toString();

    }

    public int getLiveNeighboursCount(Position position) {
        Collection<Position> adjacentPositions = adjacencyCalculator.getAdjacentPositions(position);
        return filter(adjacentPositions, new Predicate<Position>() {
            
            @Override
            public boolean apply(Position position) {
                return liveCellsMap.containsKey(position);
            }
        }).size();
    }

    public boolean isAlive(Position position) {
        return this.liveCellsMap.containsKey(position);
    }

}
