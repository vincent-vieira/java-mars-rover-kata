package io.vieira.rover.movement;

import java.util.Random;

/**
 * Direction enumeration, allowing only 4 types of {@link io.vieira.rover.Rover} facing.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public enum Direction {
    //TODO : check their order and calculations ?
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public static Direction random(){
        Direction[] values = values();
        return values[new Random().nextInt(values.length)];
    }
}
