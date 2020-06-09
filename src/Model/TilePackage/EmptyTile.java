package Model.TilePackage;

import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;
import Model.UnitPackage.Unit;

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

    @Override
    public void visit(Enemy enemy) {

    }

    @Override
    public void visit(Player player) {

    }

    @Override
    public void visit(Wall wall) {

    }

    @Override
    public void visit(EmptyTile emptyTile) {

    }
}
