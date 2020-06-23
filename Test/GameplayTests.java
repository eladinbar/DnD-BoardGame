import Controller.ActionListInput;
import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.*;
import Model.UnitPackage.PlayerPackage.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Point;
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
    public void enemyMoveEmptyTile() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Point initialEnemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-2);
        testCreator.insertEnemy(enemy, initialEnemyPosition, enemies);
        Player player = testCreator.getPlayer();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = enemy.onEnemyTurn(layout, player);
        Point finalEnemyPosition = enemy.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Enemy position expected to change but did not", true, initialEnemyPosition != finalEnemyPosition);
        Assert.assertEquals("Output was expected to be empty but contained characters", "", output);
    }

    @Test
    public void enemyMoveWall() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Player player = testCreator.getPlayer();
        Point initialEnemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-2);
        testCreator.insertEnemy(enemy, initialEnemyPosition, enemies);
        testCreator.insertWall(new Point(player.getPosition().x, (player.getPosition().y) - 1));
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = enemy.onEnemyTurn(layout, player);
        Point finalEnemyPosition = enemy.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Enemy was expected to be blocked by wall but its position changed", true, finalEnemyPosition == initialEnemyPosition);
        Assert.assertEquals("Output was expected to be empty but contained characters", "", output);
    }

    @Test
    public void enemyMoveEnemy() {
        //organize
        Enemy enemy1 = new Minion(null, Minions.LANNISTER_KNIGHT);
        Enemy enemy2 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Player player = testCreator.getPlayer();
        Point initialEnemy1Position = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-2);
        Point initialEnemy2Position = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1);
        testCreator.insertEnemy(enemy1, initialEnemy1Position, enemies);
        testCreator.insertEnemy(enemy2, initialEnemy2Position, enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = enemy1.onEnemyTurn(layout, player);
        Point finalEnemy1Position = enemy1.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Enemy was expected to be hindered by enemy but its position changed", true, finalEnemy1Position == initialEnemy1Position);
        Assert.assertEquals("Output of interaction with another enemy was expected to be empty but contained characters", "", output);
    }

    @Test
    public void enemyApproachPlayer() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Player player = testCreator.getPlayer();
        testCreator.insertEnemy(enemy, new Point(player.getPosition().x, (player.getPosition().y)-2), enemies);
        double initialRangeFromPlayer = enemy.range(testCreator.getPlayer());
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = enemy.onEnemyTurn(layout, player);
        double finalRangeFromPlayer = enemy.range(player);
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Enemy expected to approach player but moved away from him", true, finalRangeFromPlayer < initialRangeFromPlayer);
        Assert.assertEquals("Output was expected to be empty but contained characters", "", output);
    }

    @Test
    public void enemyApproachPlayerHindered() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Player player = testCreator.getPlayer();
        testCreator.insertEnemy(enemy, new Point(player.getPosition().x, (player.getPosition().y) - 2), enemies);
        testCreator.insertWall(new Point(player.getPosition().x, (player.getPosition().y) - 1));
        double initialRangeFromPlayer = enemy.range(testCreator.getPlayer());
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = enemy.onEnemyTurn(layout, player);
        double finalRangeFromPlayer = enemy.range(player);
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Enemy expected to be hindered by wall but moved from original position", true, finalRangeFromPlayer == initialRangeFromPlayer);
        Assert.assertEquals("Output was expected to be empty but contained characters", "", output);
    }

    @Test
    public void enemyEngagePlayer() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Player player = testCreator.getPlayer();
        Point initialEnemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1);
        testCreator.insertEnemy(enemy, initialEnemyPosition, enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = enemy.onEnemyTurn(layout, player);
        Point finalEnemyPosition = enemy.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Enemy expected to remain in same position but moved to a different location", true, finalEnemyPosition == initialEnemyPosition);
        Assert.assertNotEquals("Output expected to contain engage information but was empty", "", output);
    }

    @Test
    public void enemyEngageKillPlayer() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Player player = testCreator.getPlayer();
        Point initialEnemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1);
        testCreator.insertEnemy(enemy, initialEnemyPosition, enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        player.setCurrentHealth(0);
        String output = enemy.onEnemyTurn(layout, player);
        Point finalEnemyPosition = enemy.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Enemy expected to remain in same position but moved to a different location", true, finalEnemyPosition == initialEnemyPosition);
        Assert.assertNotEquals("Output expected to contain engage information but was empty", "", output);
        Assert.assertEquals("Player symbol expected to be dead but was alive", 'X', PlayerStatus.DEAD.getPlayerStatus());
    }

    @Test
    public void bossCastAbility() {
        //organize
        Boss boss = new Boss(null, Bosses.THE_MOUNTAIN);
        Player player = testCreator.getPlayer();
        Point originalPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = new EmptyTile(originalPlayerPosition);
        player.setPosition(new Point(player.getPosition().x, (player.getPosition().y)+1));
        layout[player.getPosition().x][player.getPosition().y] = player;
        Point initialEnemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-4);
        testCreator.insertEnemy(boss, initialEnemyPosition, enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        for (int i=0; i<3; i++)
            boss.onEnemyTurn(layout, player);
        String output = boss.onEnemyTurn(layout, player);
        Point finalEnemyPosition = boss.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Point expectedEnemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1);
        Assert.assertEquals("Enemy expected to approach player but moved in a different direction", expectedEnemyPosition, finalEnemyPosition);
        Assert.assertEquals("Output expected to contain cast ability information but contained other output", true, output.contains("ability"));
    }

    @Test
    public void playerEngageEnemy() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        Point enemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1);
        testCreator.insertEnemy(enemy, enemyPosition, enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = player.onPlayerTurn(layout, ActionListInput.Up, enemies);
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player expected to remain in the same position but moved to a different location", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertNotEquals("Output expected to contain engage information but was empty", "", output);
    }

    @Test
    public void playerEngageKillEnemy() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_SOLDIER);
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        int initialExp = player.getCurrentExp();
        Point enemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1);
        testCreator.insertEnemy(enemy, enemyPosition, enemies);
        Point initialEnemyPosition = enemy.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        enemy.setCurrentHealth(0);
        String output = player.onPlayerTurn(layout, ActionListInput.Up, enemies);
        Point finalPlayerPosition = player.getPosition();
        int finalExp = player.getCurrentExp();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player expected to move to enemy position but is in a different location", true, finalPlayerPosition == initialEnemyPosition);
        Assert.assertNotEquals("Output expected to contain engage information but was empty", "", output);
        Assert.assertNotEquals("Enemies list expected to not contain enemy but enemies contains enemy", true, enemies.contains(enemy));
        Assert.assertEquals("Player expected to gain enemy experience value amount of exp but value was found to be different", true, finalExp == initialExp + enemy.getExperienceValue());
    }

    @Test
    public void playerMoveEmptyTile() {
        //organize
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = player.onPlayerTurn(layout, ActionListInput.Down, enemies);
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player expected to move from original position but remained in original location", true, initialPlayerPosition != finalPlayerPosition);
        Assert.assertEquals("Output expected to be empty but contained characters", "", output);
    }

    @Test
    public void playerMoveWall() {
        //organize
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        testCreator.insertWall(new Point(player.getPosition().x, player.getPosition().y-1));
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output = player.onPlayerTurn(layout, ActionListInput.Up, enemies);
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player was expected to be hindered by wall but moved from original position", true, initialPlayerPosition == finalPlayerPosition);
        Assert.assertEquals("Output expected to be empty but contained characters", "", output);
    }

    @Test
    public void warriorCastAbility() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        int initialHealth = player.getCurrentHealth();
        Point enemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1);
        testCreator.insertEnemy(enemy, enemyPosition, enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals("Parameters were appropriate but castAbility threw an exception",false, true);
        }
        Point finalPlayerPosition = player.getPosition();
        int finalHealth = player.getCurrentHealth();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain multiple lines but contained at most 1", true, output.contains("\n"));
        Assert.assertEquals("Player final health was expected to be equal or larger than initial health by 40 but was different", true, finalHealth >= initialHealth & finalHealth <= initialHealth+40);
    }

    @Test
    public void warriorCastAbilityNoEnemy() {
        //organize
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals("Parameters were appropriate but castAbility threw an exception",false, true);
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain one line but contained multiple ones", false, output.contains("\n"));
    }

    @Test
    public void warriorCastAbilityRemainingCooldown() {
        //organize
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        //cast ability first time
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            //assert
            Assert.assertEquals("Parameters were appropriate but castAbility threw an exception", false, true);
        }
        //try to case ability second time in a row
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals(true, true); //exception was thrown as expected
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain one line but contained multiple ones", false, output.contains("\n"));
    }

    @Test
    public void mageCastAbility() {
        //organize
        Enemy enemy1 = new Minion(null, Minions.LANNISTER_SOLDIER);
        testCreator.changePlayer("Mage");
        Player player = testCreator.getPlayer();
        Point originalPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = new EmptyTile(originalPlayerPosition);
        player.setPosition(new Point(player.getPosition().x, (player.getPosition().y)+3));
        Point initialPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = player;
        testCreator.insertEnemy(enemy1, new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-6), enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals("Parameters were appropriate but castAbility threw an exception",false, true);
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain multiple lines but contained at most 1", true, output.contains("\n"));
        int count = 0;
        for (Enemy enemy : enemies)
            if (enemy.getCurrentHealth() < enemy.getHealthPool())
                count++;
        Assert.assertEquals("Enemies hit expected to be larger than 0 but was 0", true, count > 0);
    }

    @Test
    public void mageCastAbilityNoEnemy() {
        //organize
        testCreator.changePlayer("Mage");
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals("Parameters were appropriate but castAbility threw an exception",false, true);
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain one line but contained multiple ones", false, output.contains("\n"));
    }

    @Test
    public void mageCastAbilityNoMana() {
        //organize
        testCreator.changePlayer("Mage");
        Mage player = (Mage)testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        player.setCurrentMana(29);
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals(true, true); //exception was thrown as expected
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain one line but contained multiple ones", false, output.contains("\n"));
    }

    @Test
    public void rogueCastAbility() {
        //organize
        Enemy enemy1 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Enemy enemy2 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Enemy enemy3 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Enemy enemy4 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Enemy enemy5 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Enemy enemy6 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Enemy enemy7 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Enemy enemy8 = new Minion(null, Minions.LANNISTER_SOLDIER);
        Enemy enemy9 = new Minion(null, Minions.LANNISTER_SOLDIER);
        testCreator.changePlayer("Rogue");
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        testCreator.insertEnemy(enemy1, new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1), enemies);
        testCreator.insertEnemy(enemy2, new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)+1), enemies);
        testCreator.insertEnemy(enemy3, new Point(testCreator.getPlayer().getPosition().x-1, testCreator.getPlayer().getPosition().y), enemies);
        testCreator.insertEnemy(enemy4, new Point(testCreator.getPlayer().getPosition().x+1, testCreator.getPlayer().getPosition().y), enemies);
        testCreator.insertEnemy(enemy5, new Point((testCreator.getPlayer().getPosition().x)-1, (testCreator.getPlayer().getPosition().y)-1), enemies);
        testCreator.insertEnemy(enemy6, new Point((testCreator.getPlayer().getPosition().x)-1, (testCreator.getPlayer().getPosition().y)+1), enemies);
        testCreator.insertEnemy(enemy7, new Point((testCreator.getPlayer().getPosition().x)+1, (testCreator.getPlayer().getPosition().y)-1), enemies);
        testCreator.insertEnemy(enemy8, new Point((testCreator.getPlayer().getPosition().x)+1, (testCreator.getPlayer().getPosition().y)+1), enemies);
        testCreator.insertEnemy(enemy9, new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-2), enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals("Parameters were appropriate but castAbility threw an exception",false, true);
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain multiple lines but contained at most 1", true, output.contains("\n"));
        for (Enemy enemy : enemies)
            Assert.assertEquals("Enemy health was expected to be damaged by ability but was not changed", true, enemy.getCurrentHealth() < enemy.getHealthPool());
    }

    @Test
    public void rogueCastAbilityNoEnemy() {
        //organize
        testCreator.changePlayer("Rogue");
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals("Parameters were appropriate but castAbility threw an exception",false, true);
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain one line but contained multiple ones", false, output.contains("\n"));
    }

    @Test
    public void rogueCastAbilityNoEnergy() {
        //organize
        testCreator.changePlayer("Rogue");
        Rogue player = (Rogue)testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        player.setCurrentEnergy(49);
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals(true, true); //exception was thrown as expected
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain one line but contained multiple ones", false, output.contains("\n"));
    }

    @Test
    public void hunterCastAbility() {
        //organize
        Enemy enemy1 = new Minion(null, Minions.LANNISTER_SOLDIER);
        testCreator.changePlayer("Hunter");
        Player player = testCreator.getPlayer();
        Point originalPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = new EmptyTile(originalPlayerPosition);
        player.setPosition(new Point(player.getPosition().x, (player.getPosition().y)+3));
        Point initialPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = player;
        testCreator.insertEnemy(enemy1, new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-6), enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals("Parameters were appropriate but castAbility threw an exception",false, true);
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain multiple lines but contained at most 1", true, output.contains("\n"));
    }

    @Test
    public void hunterCastAbilityNoEnemyInRange() {
        //organize
        Enemy enemy1 = new Minion(null, Minions.LANNISTER_SOLDIER);
        testCreator.changePlayer("Hunter");
        Player player = testCreator.getPlayer();
        Point originalPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = new EmptyTile(originalPlayerPosition);
        player.setPosition(new Point(player.getPosition().x, (player.getPosition().y)+3));
        Point initialPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = player;
        testCreator.insertEnemy(enemy1, new Point(testCreator.getPlayer().getPosition().x+1, (testCreator.getPlayer().getPosition().y)-6), enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals(true, true); //No enemies in range, function threw exception as expected
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain one line but contained multiple ones", false, output.contains("\n"));
    }

    @Test
    public void hunterCastAbilityNoArrows() {
        //organize
        Enemy enemy1 = new Minion(null, Minions.LANNISTER_SOLDIER);
        testCreator.changePlayer("Hunter");
        Hunter player = (Hunter)testCreator.getPlayer();
        Point originalPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = new EmptyTile(originalPlayerPosition);
        player.setPosition(new Point(player.getPosition().x, (player.getPosition().y)+3));
        Point initialPlayerPosition = player.getPosition();
        layout[player.getPosition().x][player.getPosition().y] = player;
        testCreator.insertEnemy(enemy1, new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-6), enemies);
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        String output;
        player.setArrowsCount(0);
        try {
            output = player.castAbility(layout, enemies);
        }
        catch (Exception ex) {
            output = ex.getMessage();
            //assert
            Assert.assertEquals(true, true); //exception was thrown as expected
        }
        Point finalPlayerPosition = player.getPosition();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player position expected to remain unchanged but final player position was different", true, finalPlayerPosition == initialPlayerPosition);
        Assert.assertEquals("Output expected to contain one line but contained multiple ones", false, output.contains("\n"));
    }

    @Test
    public void playerLevelUp() {
        //organize
        Enemy enemy = new Minion(null, Minions.LANNISTER_KNIGHT);
        Player player = testCreator.getPlayer();
        Point initialPlayerPosition = player.getPosition();
        int initialExp = player.getCurrentExp();
        int initialLevel = player.getLevel();
        Point enemyPosition = new Point(testCreator.getPlayer().getPosition().x, (testCreator.getPlayer().getPosition().y)-1);
        testCreator.insertEnemy(enemy, enemyPosition, enemies);
        Point initialEnemyPosition = enemy.getPosition();
        //print state
        System.out.println("After organize layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        //act
        enemy.setCurrentHealth(0);
        String output = player.onPlayerTurn(layout, ActionListInput.Up, enemies);
        Point finalPlayerPosition = player.getPosition();
        int finalExp = player.getCurrentExp();
        int finalLevel = player.getLevel();
        //print state
        System.out.println("After act layout");
        testCreator.getBaseLevel().getBoard().printInfo();
        System.out.println(output);
        //assert
        Assert.assertEquals("Player expected to move to enemy position but is in a different location", true, finalPlayerPosition == initialEnemyPosition);
        Assert.assertEquals("Output expected to contain level up information but contained only other information", true, output.contains("reached level"));
        Assert.assertNotEquals("Enemies list expected to not contain enemy but enemies contains enemy", true, enemies.contains(enemy));
        Assert.assertEquals("Player expected current exp to increase by enemy experience value -50*level but value was different", true, finalExp == initialExp + enemy.getExperienceValue() - (50*(player.getLevel()-1)));
        Assert.assertEquals("Player level expected to increase but remained unchanged", true, finalLevel > initialLevel);
    }

    @After
    public void tearDown() {
        enemies = new ArrayList<>();
        layout = testCreator.getBaseLevel().getBoard().getLayout();
    }
}
