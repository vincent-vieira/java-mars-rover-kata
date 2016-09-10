package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test checking if all rover's movement routines are valid, when the rover's heading to the east.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public class RoverMovementFromEastSpec {

    private Rover roverEngine;
    private final List<Point> obstacles = Arrays.asList(new Point(4, 4), new Point(5, 4));
    private final Point initialRoverPosition = new Point(2, 2);

    @Before
    public void setupRover(){
        Planet visitedPlanet = new Planet(6, 6, obstacles);
        roverEngine = new Rover(visitedPlanet, initialRoverPosition, Direction.EAST);
    }

    @Test
    public void roverShouldBeAbleOfMovingForwardInternally(){
        roverEngine.moveForward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 3)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.EAST);
    }

    @Test
    public void roverShouldBeAbleOfMovingForwardUsingPublicAPI(){
        roverEngine.receiveCommand('F');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 3)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.EAST);
    }

    @Test
    public void roverShouldBeAbleOfMovingBackwardInternally(){
        roverEngine.moveBackward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 1)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.WEST);
    }

    @Test
    public void roverShouldBeAbleOfMovingBackwardUsingPublicAPI(){
        roverEngine.receiveCommand('B');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 1)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.WEST);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheLeftInternally(){
        roverEngine.moveLeft();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheLeftUsingPublicAPI(){
        roverEngine.receiveCommand('L');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheRightInternally(){
        roverEngine.moveRight();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 1)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheRightUsingPublicAPI(){
        roverEngine.receiveCommand('R');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 1)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingMultipleTimesInternally(){
        roverEngine.moveRight();
        roverEngine.moveLeft();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveBackward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 5)
                .hasFieldOrPropertyWithValue("position.y", 1)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.WEST);
    }

    @Test
    public void roverShouldBeAbleOfMovingMultipleTimesUsingPublicAPI(){
        roverEngine.receiveCommands("RLFFFB");
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 5)
                .hasFieldOrPropertyWithValue("position.y", 1)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.WEST);
    }

    @Test
    public void roverShouldFollowEdgeWrappingInternally(){
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 3)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.EAST);
    }

    @Test
    public void roverShouldFollowEdgeWrappingUsingPublicAPI(){
        roverEngine.receiveCommands("FFFFFFF");
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 3)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.EAST);
    }

    @Test
    public void roverShouldStopWhenHeMeetsAnObstacleInternally(){
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveLeft();
        assertThat(roverEngine.moveForward()).isEqualTo(false);
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 4)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldStopWhenHeMeetsAnObstacleUsingPublicAPI(){
        roverEngine.receiveCommands("FFL");
        assertThat(roverEngine.receiveCommand('F')).isEqualTo(false);
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 4)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }
}
