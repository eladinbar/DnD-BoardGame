package Controller;

import Model.UnitPackage.PlayerPackage.*;
import Model.UnitPackage.EnemyPackage.*;

public interface DeathObserver {
    void update(Enemy enemy);
    void update(Player player);
}
