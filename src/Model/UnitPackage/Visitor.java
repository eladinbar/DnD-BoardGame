package Model.UnitPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Wall;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;

public interface Visitor {
    void visit(Enemy enemy);
    void visit(Player player);
    void visit(Wall wall);
    void visit(EmptyTile emptyTile);
}
