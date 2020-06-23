import Controller.LevelCreationPackage.LevelCreator;
import Model.TilePackage.Tile;
import Model.TilePackage.Wall;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.*;
import View.Level;
import java.util.List;

import java.awt.Point;

public class BoardTestCreator {

    private Level baseLevel;
    private final String path = System.getProperty("user.dir") + "\\Test\\TestBoard.txt";
    private Player player;

    public BoardTestCreator() {
        player = new Warrior(null, Warriors.JON_SNOW);
        baseLevel = new LevelCreator().decipherLevel(path, player);
        System.out.println("Initial layout");
        getBaseLevel().getBoard().printInfo();
    }

    public void insertEnemy(Enemy enemy, Point position, List<Enemy> enemyList) {
        Tile[][] layout = baseLevel.getBoard().getLayout();
        layout[position.x][position.y] = enemy;
        enemy.setPosition(position);
        enemyList.add(enemy);

    }

    public void changePlayer(String newPlayer) {
        switch(newPlayer) {
            case "Mage":
                player = new Mage(getPlayer().getPosition(), Mages.MELISANDRE);
                break;
            case "Rogue":
                player = new Rogue(getPlayer().getPosition(), Rogues.BRONN);
                break;
            case "Hunter":
                player = new Hunter(getPlayer().getPosition(), Hunters.YGRITTE);
                break;
            default:
                player = new Warrior(getPlayer().getPosition(), Warriors.JON_SNOW);
        }
    }

    public void insertWall(Point position) {
        Tile[][] layout = baseLevel.getBoard().getLayout();
        layout[position.x][position.y] = new Wall(position);
    }

    public void insertWalls(Point startPosition, int numberOfWalls, boolean vertical) {
        Tile[][] layout = baseLevel.getBoard().getLayout();
        if (vertical) {
            for (int i = 0; i <= numberOfWalls; i++) {
                layout[startPosition.x][startPosition.y + i] = new Wall(new Point(startPosition.x, startPosition.y + i));
            }
        } else {
            for (int i = 0; i <= numberOfWalls; i++) {
                layout[startPosition.x + i][startPosition.y] = new Wall(new Point(startPosition.x + i, startPosition.y));
            }
        }
    }

    public Level getBaseLevel() {
        return baseLevel;
    }

    public String getPath() {
        return path;
    }

    public Player getPlayer() {
        return player;
    }
}
