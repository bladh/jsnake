package ninja.bladh.jsnake;

public class Configuration {
    /**
     * Width of the board in cells.
     */
    public final int boardWidth;
    /**
     * Height of the board in cells.
     */
    public final int boardHeight;
    /**
     * Size of a cell in pixels (all cells are squares).
     */
    public final int cellSize;
    /**
     * Interval between game steps/ticks in milliseconds.
     */
    public final long stepIntervalMs;

    public Configuration(int boardWidth, int boardHeight, int cellSize, long stepIntervalMs) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.cellSize = cellSize;
        this.stepIntervalMs = stepIntervalMs;
    }
}
