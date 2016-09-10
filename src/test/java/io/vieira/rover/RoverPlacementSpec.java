package io.vieira.rover;

import io.vieira.rover.movement.Direction;
import io.vieira.rover.movement.Point;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit test checking if all the rover's basic placement routines are valid.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
public class RoverPlacementSpec {

    private Rover roverEngine;
    private Point initialRoverCoordinates;
    private Direction facingRoverDirection = Direction.random();
    private final Planet visitedPlanet = new Planet(6, 6, Collections.emptyList());

    @Before
    public void setupRandomPlacement(){
        Random random = new Random();
        initialRoverCoordinates = new Point(random.nextInt(visitedPlanet.getWidth()), random.nextInt(visitedPlanet.getHeight()));
        this.roverEngine = new Rover(visitedPlanet, initialRoverCoordinates, facingRoverDirection);
    }

    @Test
    public void pointShouldOnlyAcceptPositiveIntegers(){
        assertThat(new Point(1, 0))
                .hasFieldOrPropertyWithValue("x", 1)
                .hasFieldOrPropertyWithValue("y", 0);

        assertThatThrownBy(() -> new Point(-1, 0))
                .hasMessage("Supplied coordinates must be positive")
                .hasNoCause()
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void planetShouldBeProperlyInitialized(){
        assertThat(visitedPlanet)
                .hasFieldOrPropertyWithValue("width", 6)
                .hasFieldOrPropertyWithValue("height", 6)
                .hasFieldOrPropertyWithValue("obstacles", Collections.emptyList());
    }

    @Test
    public void roverShouldBeProperlyInitialized(){
        assertThat(roverEngine)
                .hasFieldOrPropertyWithValue("visitedPlanet", visitedPlanet)
                .hasFieldOrPropertyWithValue("position", initialRoverCoordinates)
                .hasFieldOrPropertyWithValue("position.x", initialRoverCoordinates.getX())
                .hasFieldOrPropertyWithValue("position.y", initialRoverCoordinates.getY())
                .hasFieldOrPropertyWithValue("facingDirection", facingRoverDirection);
    }

    @Test
    public void roverShouldNotBePlaceableOutOfBounds(){
        assertThat(new Rover(new Planet(1, 1, Collections.emptyList()), new Point(0, 0), facingRoverDirection))
                .hasFieldOrPropertyWithValue("position.x", 0)
                .hasFieldOrPropertyWithValue("position.y", 0);

        assertThatThrownBy(() -> new Rover(new Planet(1, 1, Collections.emptyList()), new Point(1, 1), facingRoverDirection))
                .hasMessage("The rover is trying to be placed outside of its planet !")
                .hasNoCause()
                .isInstanceOf(IllegalArgumentException.class);
    }
}
