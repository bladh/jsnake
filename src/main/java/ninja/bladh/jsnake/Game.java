package ninja.bladh.jsnake;

import ninja.bladh.jsnake.entity.*;

import java.util.*;

public class Game implements Runnable {
    private final long stepInterval;
    private final Renderer renderer;
    private final Random random;
    private final Board board;
    private final Controls controls;
    private final Position middle;
    private final Head head;
    private final Pellet pellet;
    private final List<Segment> segments;
    private int score;
    private int size;

    private Game(long stepInterval, int score, int size, Board board, Controls controls, Renderer renderer) {
        this.score = score;
        this.size = size;
        this.board = board;
        this.controls = controls;
        this.renderer = renderer;
        this.stepInterval = stepInterval;
        this.random = new Random();
        this.middle = board.getMiddle();
        this.head = new Head(middle);
        this.pellet = new Pellet();
        this.segments = new ArrayList<Segment>();
    }

    public static Game create(Configuration configuration, Board board, Renderer renderer, Controls controls) {
        return new Game(configuration.stepIntervalMs, 0, 2, board, controls, renderer);
    }

    private Position randomPosition() {
        return new Position(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()));
    }

    private void placePellet(Pellet pellet) {
        Position pelletPos;
        while (board.isOccupied(pelletPos = randomPosition()) && board.withinBounds(pelletPos));
        board.put(pellet, pelletPos);
    }

    public void run() {
        board.put(head, middle);
        placePellet(pellet);
        Timer timer = new Timer("gameLoop");
        Step gameStep = new Step();
        timer.scheduleAtFixedRate(gameStep, 1000, stepInterval);
    }

    private class Step extends TimerTask {
        public void run() {
            final Direction direction = controls.getDirection();
            if (direction != null) {
                head.setDirection(direction);
            }
            final Position newPos = head.tick();
            if (board.hasPellet(newPos)) {
                size++;
                score++;
                placePellet(pellet);
                for (Segment segment : segments) {
                    segment.extendLife();
                }
            }
            final Iterator<Segment> iterator = segments.iterator();
            while (iterator.hasNext()) {
                final Segment segment = iterator.next();
                final boolean alive = segment.tick();
                if (!alive) {
                    iterator.remove();
                    board.remove(segment);
                }
            }
            final Position oldPos = head.getPosition();
            if (!board.withinBounds(newPos) || board.hasSegment(newPos)) {
                System.out.println("Game over!");
                this.cancel();
            }
            head.setPosition(newPos);
            board.clear(oldPos);
            board.put(head, newPos);
            final Segment newSegment = new Segment(size);
            board.put(newSegment, oldPos);
            segments.add(newSegment);
            renderer.render(board.getPositions(), score);
        }
    }
}
