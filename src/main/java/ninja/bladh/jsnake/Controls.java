package ninja.bladh.jsnake;

import ninja.bladh.jsnake.entity.Direction;

public interface Controls {
    /**
     * Get the last pushed direction key to be processed during the current time step.
     */
    Direction getDirection();
}
