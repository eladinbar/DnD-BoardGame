package Model.UnitPackage;

import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;

public interface Combatant {
    String engage(Enemy enemy);
    String engage(Player player);
}
