package Model.UnitPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Wall;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;

public interface Visitor {
    String visit(Enemy enemy);
    String visit(Player player);
    String visit(Wall wall);
    String visit(EmptyTile emptyTile);
}
