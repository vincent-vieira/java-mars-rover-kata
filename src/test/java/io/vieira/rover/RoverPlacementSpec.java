package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test checking if all the rover's basic placement routines are valid.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public class RoverPlacementSpec {

    private Rover roverEngine;
    private Point initialRoverCoordinates;
    private Direction facingRoverDirection = Direction.random();

    @Before
    public void setupRandomPlacement(){
        Random random = new Random();
        initialRoverCoordinates = new Point(random.nextInt(10), random.nextInt(1));
        this.roverEngine = new Rover(initialRoverCoordinates, facingRoverDirection);
    }

    @Test
    public void isRoverProperlyPlaced(){
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position", initialRoverCoordinates)
                .hasFieldOrPropertyWithValue("position.x", initialRoverCoordinates.getX())
                .hasFieldOrPropertyWithValue("position.y", initialRoverCoordinates.getY());
    }

    @Test
    public void isRoverFacingTheRightDirection(){
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("facingDirection", facingRoverDirection);
    }
}