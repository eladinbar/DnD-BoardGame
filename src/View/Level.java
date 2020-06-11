package View;

import Model.UnitPackage.EnemyPackage.Enemy;
import java.util.List;

public class Level {

    private GameBoard board;
    private List<Enemy> enemyList;

    public Level(GameBoard board, List<Enemy> enemies) {
        this.board = board;
        this.enemyList = enemies;
    }

    public GameBoard getBoard() {
        return board;
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }
}
