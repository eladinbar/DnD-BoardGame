import Controller.LevelCreationPackage.LevelCreator;
import Model.TilePackage.Tile;
import Model.TilePackage.Wall;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.PlayerPackage.Player;
import Model.UnitPackage.PlayerPackage.Warrior;
import Model.UnitPackage.PlayerPackage.Warriors;
import View.Level;
import java.util.List;

import java.awt.*;

public class BoardTestCreator {

    private Level baseLevel;
    private final String path = "C:\\Users\\eladi\\IdeaProjects\\DnD-BoardGame\\Test\\TestBoard.txt";
    private Player player;

    public BoardTestCreator() {
        player = new Warrior(null, Warriors.JON_SNOW);
        baseLevel = new LevelCreator().decipherLevel(path, player);
    }

    public void insertEnemy(Enemy enemy, Point position, List<Enemy> enemyList) {
        Tile[][] layout = baseLevel.getBoard().getLayout();
        layout[position.x][position.y] = enemy;
        enemyList.add(enemy);

    }

    public void changePlayer(Player newPlayer) {
        newPlayer.setPosition(player.getPosition());
        player = newPlayer;
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
