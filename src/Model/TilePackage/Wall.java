package Model.TilePackage;

import Model.UnitPackage.Visitor;

import java.awt.Point;

public class Wall extends Tile {
    public Wall(Point position) {
        super(position);
        symbol = '#';
    }

    @Override
    public void accept(Visitor visitor) {
        //Do nothing
    }
}
