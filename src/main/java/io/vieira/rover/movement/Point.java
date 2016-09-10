package io.vieira.rover.movement;

import lombok.Getter;

/**
 * Class representing the x and y coordinates of the {@link io.vieira.rover.Rover}.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
@Getter
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        if(x < 0 || y < 0){
            throw new IllegalArgumentException("Supplied coordinates must be positive");
        }
        this.x = x;
        this.y = y;
    }
}
