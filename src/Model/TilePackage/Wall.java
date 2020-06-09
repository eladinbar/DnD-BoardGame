package Model.TilePackage;

import Model.UnitPackage.Unit;

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

    @Override
    public void visit(Unit unit) {

    }

    @Override
    public void visit(Wall wall) {

    }

    @Override
    public void visit(EmptyTile emptyTile) {

    }
}
