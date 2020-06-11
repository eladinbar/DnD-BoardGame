package Model.UnitPackage;

import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;

import java.util.List;

public interface HeroicUnit {
    default String castAbility(Tile[][] layout, List<Enemy> enemies) throws Exception {
        return null; //Do nothing
    }

    default String castAbility(Tile[][] layout, Player player) throws Exception {
        return null; //Do nothing
    }
}
