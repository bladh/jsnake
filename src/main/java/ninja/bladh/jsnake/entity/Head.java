package ninja.bladh.jsnake.entity;

public class Head extends Piece {
    private Position position;
    private Direction direction;

    public Head(Position initialPosition) {
        position = initialPosition;
        direction = Direction.RIGHT;
    }

    public Position tick() {
        final Position newPosition;
        switch (direction) {
            case RIGHT:
                newPosition = new Position(position.x + 1, position.y);
                break;
            case DOWN:
                newPosition = new Position(position.x, position.y + 1);
                break;
            case LEFT:
                newPosition = new Position(position.x - 1, position.y);
                break;
            case UP:
                newPosition = new Position(position.x, position.y - 1);
                break;
            default:
                throw new IllegalStateException("Tick'd in unknown direction");

        }
        return newPosition;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * there can be only one, mr anderson...
     */
    @Override
    public boolean equals(Object o) {
        return this == o || !(o == null || getClass() != o.getClass());
    }
}
