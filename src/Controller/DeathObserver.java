package Controller;

import Model.Enemy;
import Model.Player;

public interface DeathObserver {
    void update(Enemy enemy);
    void update(Player player);
}
