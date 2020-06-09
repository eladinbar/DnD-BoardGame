package Model.TilePackage;

import java.awt.Point;

public abstract class Tile implements Visited {
    public Point position;
    protected char symbol;

    public Tile(Point position) {
        this.position = position;
    }

    protected double range(Tile other) {
        return Math.sqrt(Math.pow(this.position.x - other.position.x, 2) + Math.pow(this.position.y - other.position.y, 2));
    }

    public String toString() {
        //Returns the tile character. Use it to print the board.
        throw new UnsupportedOperationException();
    }
}
