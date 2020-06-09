package Model.TilePackage;

import Model.UnitPackage.Visitor;

import java.awt.Point;

public class EmptyTile extends Tile {
    public EmptyTile(Point position) {
        super(position);
        symbol = '.';
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
