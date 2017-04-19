package ninja.bladh.jsnake;

import ninja.bladh.jsnake.entity.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {
    private final int width;
    private final int height;
    private Map<Position, Piece> boardMap;

    private Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.boardMap = new HashMap<Position, Piece>();
    }

    public static Board create(Configuration configuration) {
        final Board board = new Board(configuration.boardWidth, configuration.boardHeight);
        board.clearBoard();
        return board;
    }

    public void clearBoard() {
        boardMap.clear();
    }

    public Position getMiddle() {
        return new Position(width / 2, height / 2);
    }

    public boolean isOccupied(final Position position) {
        return boardMap.containsKey(position);
    }

    public boolean hasSegment(final Position position) {
        if (boardMap.containsKey(position)) {
            if (boardMap.get(position) instanceof Segment) {
                return true;
            }
        }
        return false;
    }

    public void put(Piece piece, Position position) {
        boardMap.put(position, piece);
    }

    public void clear(Position position) {
        boardMap.remove(position);
    }

    public void remove(Piece piece) {
        final Set<Position> positionSet = boardMap.keySet();
        for (Position position : positionSet) {
            if (boardMap.get(position).equals(piece)) {
                boardMap.remove(position);
                return;
            }
        }
    }

    public boolean withinBounds(Position position) {
        return position.x >= 0 && position.y >= 0 && position.x <= width && position.y <= height;
    }

    public Collection<Position> getPositions() {
        return boardMap.keySet();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean hasPellet(Position position) {
        if (boardMap.containsKey(position)) {
            if (boardMap.get(position) instanceof Pellet) {
                return true;
            }
        }
        return false;
    }
}
