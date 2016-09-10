package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;

/**
 * Rover engine main class.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public class Rover {

    private final Planet visitedPlanet;
    private Point position;
    private Direction facingDirection;

    public Rover(Planet visitedPlanet, Point point, Direction facingDirection) {
        if(point.getX() >= visitedPlanet.getWidth() || point.getY() >= visitedPlanet.getHeight()){
            throw new IllegalArgumentException("The rover is trying to be placed outside of its planet !");
        }
        this.visitedPlanet = visitedPlanet;
        this.position = point;
        this.facingDirection = facingDirection;
    }
}
