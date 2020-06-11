package Model.UnitPackage;

import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;

public interface Combatant {
    default String engage(Enemy enemy) {
        return null; //Do nothing
    }

    default String engage(Player player) {
        return null; //Do nothing
    }
}
