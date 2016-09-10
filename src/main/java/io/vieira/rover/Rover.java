package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;

/**
 * Rover engine main class. Only the necessary public methods are exposed, all remaining ones are designed to be an internal API (for tests, as an example).
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public final class Rover {

    private final Planet visitedPlanet;
    private Point position;
    private Direction facingDirection;

    public Rover(Planet visitedPlanet, Point point, Direction facingDirection) {
        if(point.getX() >= visitedPlanet.getWidth() || point.getY() >= visitedPlanet.getHeight()){
            throw new IllegalArgumentException("The Rover is trying to be placed outside of its planet !");
        }
        if(visitedPlanet.getObstacles().indexOf(point) != -1){
            throw new IllegalArgumentException("The Rover can't be put on an obstacle.");
        }
        this.visitedPlanet = visitedPlanet;
        this.position = point;
        this.facingDirection = facingDirection;
    }

    /**
     * Receives a single command to move the rover.
     *
     * @param command the character representing the command. Valid values are : F,B,L,R, nominatively for Forward-Backward-Left-Right. Any invalid command will throw an {@link IllegalArgumentException}.
     * @return a boolean showing if the command succeeded or not
     */
    public final boolean receiveCommand(char command){
        switch(Character.toUpperCase(command)){
            case 'F':
                return moveForward();
            case 'B':
                return moveBackward();
            case 'L':
                return moveLeft();
            case 'R':
                return moveRight();
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }

    /**
     * Receives multiple commands and dispatch them to the rover.
     *
     * @param commands a string containing multiple characters. Any invalid command into this string will throw an {@link IllegalArgumentException}.
     * @return a boolean showing if the command succeeded or not
     */
    public final boolean receiveCommands(String commands){
        for(char command : commands.toCharArray()){
            if(!receiveCommand(command)){
                return false;
            }
        }
        return true;
    }

    boolean moveForward() {
        return move(facingDirection);
    }

    boolean moveBackward() {
        return move(facingDirection.goBackward());
    }

    boolean moveLeft() {
        return move(facingDirection.goLeft());
    }

    boolean moveRight() {
        return move(facingDirection.goRight());
    }

    private boolean move(Direction facingDirection){
        int maxX = visitedPlanet.getWidth();
        int maxY = visitedPlanet.getHeight();

        Point newPosition = null;
        switch(facingDirection){
            case NORTH:
                newPosition = new Point(this.position.getX(), (this.position.getY() + 1) % maxY);
                break;
            case SOUTH:
                newPosition = new Point(this.position.getX(), (maxY + this.position.getY() - 1) % maxY);
                break;
            case WEST:
                newPosition = new Point((maxX + this.position.getX() - 1) % maxX, this.position.getY());
                break;
            case EAST:
                newPosition = new Point((this.position.getX() + 1) % maxX, this.position.getY());
                break;
        }

        boolean newPositionHasObstacle = this.visitedPlanet.getObstacles().indexOf(newPosition) != -1;
        if(!newPositionHasObstacle){
            this.position = newPosition;
            this.facingDirection = facingDirection;
        }
        return !newPositionHasObstacle;
    }
}
