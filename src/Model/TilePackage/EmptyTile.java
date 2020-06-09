package Model.TilePackage;

import java.awt.Point;

public class EmptyTile extends Tile {
    public EmptyTile(Point position) {
        super(position);
        symbol = '.';
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
