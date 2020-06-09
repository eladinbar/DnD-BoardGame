package Model.TilePackage;

import java.awt.*;

public class EmptyTile extends Tile {
    public EmptyTile(Point position, char symbol) {
        super(position);
        symbol = '#';
    }

    @Override
    public void accept(Visitor visitor) {

    }
}
