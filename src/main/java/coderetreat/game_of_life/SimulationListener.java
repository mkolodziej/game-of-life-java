package coderetreat.game_of_life;

public interface SimulationListener {
    void newBoardState(int iteration, Board board);
}
