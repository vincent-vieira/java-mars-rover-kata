package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;
import lombok.Getter;

/**
 * Rover engine main class.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
@Getter
public class Rover {

    private final Point position;
    private final Direction facingDirection;

    public Rover(Point point, Direction random) {
        position = point;
        facingDirection = random;
    }
}
