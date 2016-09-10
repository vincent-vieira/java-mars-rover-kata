package io.vieira.rover;

import io.vieira.rover.movement.Point;
import lombok.Getter;

import java.util.List;

/**
 * Planet class containing the size of the world the {@link Rover} can explore, as well as the different obstacles.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
@Getter
public class Planet {

    private final int width;
    private final int height;
    private final List<Point> obstacles;

    public Planet(int width, int height, List<Point> obstacles) {
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
    }
}
