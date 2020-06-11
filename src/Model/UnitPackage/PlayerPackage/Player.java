package Model.UnitPackage.PlayerPackage;

import Controller.ActionListInput;
import Model.ANSIColors;
import Model.Result;
import Model.TickListener;
import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.UnitPackage.Visitor;
import Model.TilePackage.Wall;
import Model.UnitPackage.EnemyPackage.Enemy;
import Model.UnitPackage.HeroicUnit;
import Model.UnitPackage.Unit;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Unit implements HeroicUnit, Visitor, TickListener {
    protected Integer experience;
    protected Integer level;
    protected Integer experienceThreshold;

    public Player(Point position) {
        super(position);
        this.symbol = PlayerStatus.ALIVE.playerStatus;
        this.experience = 0;
        this.level = 1;
        this.experienceThreshold = 50 * level;
    }

    private String gainExp(Integer experienceValue) {
        String output = "";
        if (experience + experienceValue >= experienceThreshold) {
            output += this.levelUp();
        }
        else
            experience += experienceValue;
        return output;
    }

    public String levelUp() {
        experience -= 50 * level;
        level++;
        healthPool += 10 * level;
        currentHealth = healthPool;
        attack += 4 * level;
        defense += level;
        return ANSIColors.BRIGHT_YELLOW + name + " reached level " + level + ": ";
    }

    public void onPlayerTurn(Tile[][] layout, ActionListInput action, List<Enemy> enemies) {
        if (action == ActionListInput.Left)
            this.moveLeft(layout);
        if (action == ActionListInput.Right)
            this.moveRight(layout);
        if (action == ActionListInput.Up)
            this.moveUp(layout);
        if (action == ActionListInput.Down)
            this.moveDown(layout);
        if (action == ActionListInput.CastAbility) {
            try {
                this.castAbility(layout, enemies);
            } catch (Exception ex) {

            }
        }
    }

    protected List<Enemy> getAllEnemiesInRange(List<Enemy> enemies, int range) {
        List<Enemy> enemiesInRange = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (this.range(enemy) < range)
                enemiesInRange.add(enemy);
        }
        return enemiesInRange;
    }

    protected Enemy chooseRandomEnemy(List<Enemy> enemies, int range) {
        List<Enemy> enemiesInRange = getAllEnemiesInRange(enemies, range);
        if (!enemiesInRange.isEmpty()) {
            int random = (int) Math.random() * enemiesInRange.size();
            return enemiesInRange.get(random);
        }
        return null;
    }

    protected Enemy getClosestEnemyInRange(List<Enemy> enemies, int range) {
        Enemy closestEnemy = null;
        double closestRange = Double.MAX_VALUE;
        for (Enemy enemy : enemies) {
            double currentRange = this.range(enemy);
            if (currentRange < closestRange) {
                closestRange = currentRange;
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }

    protected void moveLeft(Tile[][] layout) {
        this.interact(layout[this.position.x-1][this.position.y]);
    }

    protected void moveRight(Tile[][] layout) {
        this.interact(layout[this.position.x+1][this.position.y]);
    }

    protected void moveUp(Tile[][] layout) {
        this.interact(layout[this.position.x][this.position.y+1]);
    }

    protected void moveDown(Tile[][] layout) {
        this.interact(layout[this.position.x][this.position.y-1]);
    }

    public void interact(Tile tile) {
        tile.accept(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String visit(Enemy enemy) {
        return this.engage(enemy);
    }

    @Override
    public String visit(Wall wall) {
        return ""; //Do nothing
    }

    @Override
    public String visit(EmptyTile emptyTile) {
        this.setPosition(emptyTile.getPosition());
        return "";
    }

    @Override
    public String engage(Enemy enemy) {
        String combatResult = ANSIColors.GREEN + this.name + " engaged in combat with " + enemy.getName() + ANSIColors.RESET
                                + "\n" + ANSIColors.BRIGHT_BLUE + this.describe() + ANSIColors.RESET
                                + "\n" + ANSIColors.BLUE + enemy.describe() + ANSIColors.RESET;
        Result attackResult = this.attack();
        int attackRoll = attackResult.getDiceRoll();
        combatResult += "\n" + attackResult.getOutput();
        Result defenseResult = enemy.defend();
        int defenseRoll = defenseResult.getDiceRoll();
        combatResult += "\n" + defenseResult.getOutput();
        int damage = attackRoll - defenseRoll;
        combatResult += "\n" + ANSIColors.BOLD + this.name + " dealt " + Math.max(damage, 0) + " damage  to " + enemy.getName() + ANSIColors.RESET;
        if (damage > 0)
            enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
        if (enemy.getCurrentHealth() <= 0)
            this.kill(enemy);
        return combatResult;
    }

    protected void kill(Enemy enemy) {
        this.gainExp(enemy.getExperienceValue());
//        EmptyTile et = new EmptyTile(this.position);
        this.setPosition(enemy.getPosition());
//        enemy.setPosition(null);
    }

    public String toString() {
        return ANSIColors.GREEN + "" + symbol + ANSIColors.RESET;
    }
}
