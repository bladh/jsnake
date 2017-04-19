package ninja.bladh.jsnake.awt;

import ninja.bladh.jsnake.Controls;
import ninja.bladh.jsnake.entity.Direction;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AWTControls implements Controls {
    private static final String UP = "UP";
    private static final String DOWN = "DOWN";
    private static final String RIGHT = "RIGHT";
    private static final String LEFT = "LEFT";
    private Direction direction;
    private Direction lastDirection;

    public AWTControls(JComponent component) {
        direction = Direction.RIGHT;
        final InputMap map = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        final ActionMap actionMap = component.getActionMap();
        map.put(KeyStroke.getKeyStroke("UP"), UP);
        map.put(KeyStroke.getKeyStroke("W"), UP);
        map.put(KeyStroke.getKeyStroke("w"), UP);
        map.put(KeyStroke.getKeyStroke("DOWN"), DOWN);
        map.put(KeyStroke.getKeyStroke("RIGHT"), RIGHT);
        map.put(KeyStroke.getKeyStroke("LEFT"), LEFT);
        actionMap.put(UP, new DirectionAction(Direction.UP));
        actionMap.put(DOWN, new DirectionAction(Direction.DOWN));
        actionMap.put(RIGHT, new DirectionAction(Direction.RIGHT));
        actionMap.put(LEFT, new DirectionAction(Direction.LEFT));
    }

    public Direction getDirection() {
        lastDirection = direction;
        return direction;
    }

    private void setDirection(Direction direction) {
        if (lastDirection == Direction.DOWN && direction == Direction.UP) {
            return;
        }
        if (lastDirection == Direction.UP && direction == Direction.DOWN) {
            return;
        }
        if (lastDirection == Direction.LEFT && direction == Direction.RIGHT) {
            return;
        }
        if (lastDirection == Direction.RIGHT && direction == Direction.LEFT) {
            return;
        }
        this.direction = direction;
    }

    private class DirectionAction extends AbstractAction {
        private final Direction actionDirection;

        DirectionAction(Direction actionDirection) {
            this.actionDirection = actionDirection;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            setDirection(actionDirection);
        }
    }
}
