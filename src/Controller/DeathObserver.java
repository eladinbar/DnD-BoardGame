package Controller;

import Model.UnitPackage.PlayerPackage.Player;
import Model.UnitPackage.EnemyPackage.Enemy;

public interface DeathObserver {
    void update(Enemy enemy);
    void update(Player player);
}
