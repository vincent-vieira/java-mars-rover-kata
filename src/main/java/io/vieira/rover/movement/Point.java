package io.vieira.rover.movement;

import lombok.Getter;

/**
 * Class representing the x and y coordinates of any object on the {@link io.vieira.rover.Planet}.
 *
 * @author <a href="mailto:vincent.vieira@supinfo.com">Vincent Vieira</a>
 */
@Getter
public final class Point {

    /**
     * Starts from 0
     */
    private final int x;

    /**
     * Starts from 0
     */
    private final int y;

    public Point(int x, int y) {
        if(x < 0 || y < 0){
            throw new IllegalArgumentException("Supplied coordinates must be positive");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
