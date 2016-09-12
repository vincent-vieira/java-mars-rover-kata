package io.vieira.rover;

import io.vieira.rover.movement.Point;
import lombok.Getter;

import java.util.Set;

/**
 * Planet class containing the size of the world the {@link Rover} can explore, as well as the different obstacles.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
@Getter
public final class Planet {

    private final int width;
    private final int height;
    private final Set<Point> obstacles;

    public Planet(int width, int height, Set<Point> obstacles) {
        this.width = width;
        this.height = height;
        for(Point obstacle : obstacles){
            if(obstacle.getX() >= width || obstacle.getY() >= height){
                throw new IllegalArgumentException(
                        String.format("Obstacle at position '%d,%d' is out of bounds", obstacle.getX(), obstacle.getY())
                );
            }
        }
        this.obstacles = obstacles;
    }
}
