package Model.TilePackage;

import java.awt.Point;

public abstract class Tile implements Visited {
    protected Point position;
    protected char symbol;

    public Tile(Point position) {
        this.position = position;
    }

    protected double range(Tile other) {
        return Math.sqrt(Math.pow(this.position.x - other.position.x, 2) + Math.pow(this.position.y - other.position.y, 2));
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void switchPosition(Tile other) {
        Point originalPosition = this.position;
        this.position = other.position;
        other.position = originalPosition;
    }

    @Override
    public String toString() {
        return "" + symbol;
    }
}
