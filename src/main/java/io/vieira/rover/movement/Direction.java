package io.vieira.rover.movement;

import java.util.Random;

/**
 * Direction enumeration, allowing only 4 types of {@link io.vieira.rover.Rover} facing.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public enum Direction {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private final int directionCode;

    Direction(int directionCode) {
        this.directionCode = directionCode;
    }

    public static Direction random(){
        Direction[] values = values();
        return values[new Random().nextInt(values.length)];
    }

    //Forward direction is the same, we don't need to create another method to expose it.
    public Direction goBackward() {
        return values()[(directionCode + 2) % 4];
    }

    public Direction goLeft(){
        return getDirection(-1);
    }

    public Direction goRight(){
        return getDirection(1);
    }

    public Direction getDirection(int directionStep){
        Direction[] values = values();
        int index = (values.length + directionCode + directionStep) % values.length;
        return values[index];
    }
}
