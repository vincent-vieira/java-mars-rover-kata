package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit test checking if all global rover's movement routines are valid.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public class RoverMovementSpec {

    private Rover roverEngine;
    private final Point initialRoverPosition = new Point(2, 2);

    @Before
    public void setupRover(){
        Planet visitedPlanet = new Planet(6, 6, Collections.emptySet());
        roverEngine = new Rover(visitedPlanet, initialRoverPosition, Direction.random());
    }

    @Test
    public void roverShouldBeOnlyRespondingToValidInstructions(){
        assertThatThrownBy(() -> roverEngine.receiveCommand('T'))
                .hasNoCause()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown command");
    }
}
