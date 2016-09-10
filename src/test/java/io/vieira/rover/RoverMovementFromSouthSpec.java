package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test checking if all rover's movement routines are valid, when the rover's heading to the south.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public class RoverMovementFromSouthSpec {

    private Rover roverEngine;
    //TODO : change obstacles position to differ from other tests
    private final List<Point> obstacles = Arrays.asList(new Point(4, 4), new Point(5, 1));
    private final Point initialRoverPosition = new Point(6, 6);

    @Before
    public void setupRover(){
        Planet visitedPlanet = new Planet(8, 8, obstacles);
        roverEngine = new Rover(visitedPlanet, initialRoverPosition, Direction.SOUTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingForwardInternally(){
        roverEngine.moveForward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 6)
                .hasFieldOrPropertyWithValue("position.y", 5)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingForwardUsingPublicAPI(){
        roverEngine.receiveCommand('F');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 6)
                .hasFieldOrPropertyWithValue("position.y", 5)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingBackwardInternally(){
        roverEngine.moveBackward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 6)
                .hasFieldOrPropertyWithValue("position.y", 7)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingBackwardUsingPublicAPI(){
        roverEngine.receiveCommand('B');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 6)
                .hasFieldOrPropertyWithValue("position.y", 7)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheLeftInternally(){
        roverEngine.moveLeft();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 7)
                .hasFieldOrPropertyWithValue("position.y", 6)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.EAST);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheLeftUsingPublicAPI(){
        roverEngine.receiveCommand('L');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 7)
                .hasFieldOrPropertyWithValue("position.y", 6)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.EAST);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheRightInternally(){
        roverEngine.moveRight();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 5)
                .hasFieldOrPropertyWithValue("position.y", 6)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.WEST);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheRightUsingPublicAPI(){
        roverEngine.receiveCommand('R');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 5)
                .hasFieldOrPropertyWithValue("position.y", 6)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.WEST);
    }

    @Test
    public void roverShouldBeAbleOfMovingMultipleTimesInternally(){
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveRight();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveBackward();
        roverEngine.moveLeft();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 4)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingMultipleTimesUsingPublicAPI(){
        roverEngine.receiveCommands("FFFFRFFBL");
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 4)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldFollowEdgeWrappingInternally(){
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 6)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldFollowEdgeWrappingUsingPublicAPI(){
        roverEngine.receiveCommands("FFFF");
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 6)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }
}
