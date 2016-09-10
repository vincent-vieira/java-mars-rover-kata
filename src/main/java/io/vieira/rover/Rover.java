package io.vieira.rover;

import io.vieira.rover.movement.Commandable;
import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;

/**
 * Rover engine main class. Only methods exposed through {@link Commandable} are exposed, all remaining ones are designed to be an internal API (for tests, as and example).
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public class Rover implements Commandable<Rover> {

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

    @Override
    public final Rover receiveCommand(char command){
        switch(Character.toUpperCase(command)){
            case 'F':
                moveForward();
                break;
            case 'B':
                moveBackward();
                break;
            case 'L':
                moveLeft();
                break;
            case 'R':
                moveRight();
                break;
            default:
                throw new IllegalArgumentException("Unknown command");
        }
        return this;
    }

    void moveForward() {
        move();
    }

    void moveBackward() {
        facingDirection = facingDirection.goBackward();
        move();
    }

    void moveLeft() {
        facingDirection = facingDirection.goLeft();
        move();
    }

    void moveRight() {
        facingDirection = facingDirection.goRight();
        move();
    }

    private void move(){
        //TODO : wrap when out of bounds ?
        switch(facingDirection){
            case NORTH:
                this.position = new Point(this.position.getX(), this.position.getY() + 1);
                break;
            case SOUTH:
                this.position = new Point(this.position.getX(), this.position.getY() - 1);
                break;
            case WEST:
                this.position = new Point(this.position.getX() - 1, this.position.getY());
                break;
            case EAST:
                this.position = new Point(this.position.getX() + 1, this.position.getY());
                break;
        }
    }
}
