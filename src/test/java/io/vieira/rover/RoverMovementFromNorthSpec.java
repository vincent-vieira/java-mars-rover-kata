package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test checking if all rover's movement routines are valid, when the rover's heading to the north.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public class RoverMovementFromNorthSpec {

    private Rover roverEngine;
    private final List<Point> obstacles = Collections.singletonList(new Point(3, 3));
    private final Point initialRoverPosition = new Point(2, 1);

    @Before
    public void setupRover(){
        Planet visitedPlanet = new Planet(4, 4, obstacles);
        roverEngine = new Rover(visitedPlanet, initialRoverPosition, Direction.NORTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingForwardInternally(){
        roverEngine.moveForward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingForwardUsingPublicAPI(){
        roverEngine.receiveCommand('F');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 2)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingBackwardInternally(){
        roverEngine.moveBackward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 0)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingBackwardUsingPublicAPI(){
        roverEngine.receiveCommand('B');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 0)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheLeftInternally(){
        roverEngine.moveLeft();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 1)
                .hasFieldOrPropertyWithValue("position.y", 1)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.WEST);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheLeftUsingPublicAPI(){
        roverEngine.receiveCommand('L');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 1)
                .hasFieldOrPropertyWithValue("position.y", 1)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.WEST);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheRightInternally(){
        roverEngine.moveRight();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 3)
                .hasFieldOrPropertyWithValue("position.y", 1)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.EAST);
    }

    @Test
    public void roverShouldBeAbleOfMovingOnTheRightUsingPublicAPI(){
        roverEngine.receiveCommand('R');
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 3)
                .hasFieldOrPropertyWithValue("position.y", 1)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.EAST);
    }

    @Test
    public void roverShouldBeAbleOfMovingMultipleTimesInternally(){
        roverEngine.moveForward();
        roverEngine.moveRight();
        roverEngine.moveRight();
        roverEngine.moveRight();
        roverEngine.moveForward();
        roverEngine.moveBackward();
        roverEngine.moveRight();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 0)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldBeAbleOfMovingMultipleTimesUsingPublicAPI(){
        roverEngine.receiveCommands("FRRRFBR");
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 0)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.SOUTH);
    }

    @Test
    public void roverShouldFollowEdgeWrappingInternally(){
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        roverEngine.moveForward();
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldFollowEdgeWrappingUsingPublicAPI(){
        roverEngine.receiveCommands("FFFFFF");
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldStopWhenHeMeetsAnObstacleInternally(){
        roverEngine.moveForward();
        roverEngine.moveForward();
        assertThat(roverEngine.moveRight()).isEqualTo(false);
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }

    @Test
    public void roverShouldStopWhenHeMeetsAnObstacleUsingPublicAPI(){
        roverEngine.receiveCommands("FF");
        assertThat(roverEngine.receiveCommand('R')).isEqualTo(false);
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("position.x", 2)
                .hasFieldOrPropertyWithValue("position.y", 3)
                .hasFieldOrPropertyWithValue("facingDirection", Direction.NORTH);
    }
}
