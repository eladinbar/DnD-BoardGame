package Model.UnitPackage;

import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;

public interface Attacker {
    void attack(Player player);
    void attack(Enemy enemy);
}
