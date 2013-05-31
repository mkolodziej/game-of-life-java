package coderetreat.game_of_life;

import static java.util.Arrays.asList;

/**
 * Main class of the program. Can run a simulation for a given number of iterations and notify the caller about every
 * state through a listener.
 * 
 * @author Maciej Kolodziej <makolodz@gmail.com>
 * 
 */
public class Game {
    
    public Board simulate(int iterationsCount, Board initialBoard) {
        return this.simulate(iterationsCount, initialBoard, new SimulationListener() {
            @Override
            public void newBoardState(int iteration, Board board) {
            }
        });
    }
    

    public Board simulate(int iterationsCount, Board initialBoard, SimulationListener simulationListener) {
        Board currentBoard = initialBoard;

        for (int i = 0; i < iterationsCount; i++) {
            currentBoard = currentBoard.calculateNextState();
            simulationListener.newBoardState(i, currentBoard);
        }
        
        return currentBoard;
    }
    
    public static void main(String[] args) {
        
        int iterationsCount = 12;
        Board initialBoard = new Board(asList(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2), new Cell(-1, 1)));
        
        System.out.println("Starting simulation for " + iterationsCount + " iterations");
        System.out.println("\nInitial board:\n" + initialBoard + "\nCells: " + initialBoard.getLiveCells());
        
        
        new Game().simulate(iterationsCount, initialBoard, new SimulationListener() {
            @Override
            public void newBoardState(int iteration, Board board) {
                System.out.println("\nAfter iteration " + (iteration + 1) + ":\n" + board + "\nCells: " + board.getLiveCells());
            }
        });
        System.out.println("\nSimulation ended.");
    }
}
