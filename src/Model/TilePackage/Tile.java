package Model.TilePackage;

import java.awt.Point;

public abstract class Tile {
    public Point position;
    protected char symbol;

    public String toString() {
        //Returns the tile character. Use it to print the board.
        throw new UnsupportedOperationException();
    }
}
