import Controller.ActionListInput;
import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.EnemyPackage.Minion;
import Model.UnitPackage.EnemyPackage.Minions;
import Model.UnitPackage.PlayerPackage.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameplayTests {
    BoardTestCreator testCreator;
    Tile[][] layout;
    List<Enemy> enemies;

    @Before
    public void initTest() {
        this.testCreator = new BoardTestCreator();
        this.layout = testCreator.getBaseLevel().getBoard().getLayout();
        this.enemies = new ArrayList<>();
    }

    @Test
    public void enemyEngage() {
        Player player = testCreator.getPlayer();
        testCreator.insertEnemy(new Minion(null, Minions.LANNISTER_KNIGHT), new Point(player.getPosition().x, (player.getPosition().y)-1), enemies);
        String output = player.onPlayerTurn(layout, ActionListInput.Up, enemies);
        Assert.assertNotEquals("Player did not engage enemy", "", output);
    }

    @After
    public void tearDown() {
        enemies = new ArrayList<>();
        layout = testCreator.getBaseLevel().getBoard().getLayout();
    }
}
