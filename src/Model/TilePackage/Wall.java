package Model.TilePackage;

import java.awt.Point;

public class Wall extends Tile {
    public Wall(Point position) {
        super(position);
        symbol = '#';
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
