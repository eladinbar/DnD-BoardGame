package Model.UnitPackage.PlayerPackage;

import Controller.ActionListInput;
import Model.ANSIColors;
import Model.Result;
import Model.UnitPackage.CombatResult;
import Model.UnitPackage.TickListener;
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

    public String onPlayerTurn(Tile[][] layout, ActionListInput action, List<Enemy> enemies) {
        Point originalPosition = this.position;
        String output="";
        if (action == ActionListInput.Left)
            output = this.moveLeft(layout);
        else if (action == ActionListInput.Right)
            output = this.moveRight(layout);
        else if (action == ActionListInput.Up)
            output = this.moveUp(layout);
        else if (action == ActionListInput.Down)
            output = this.moveDown(layout);
        else if (action == ActionListInput.CastAbility) {
            try {
                output = this.castAbility(layout, enemies);
            } catch (Exception ex) {
                output = ex.getMessage();
            }
        }
        if (!this.position.equals(originalPosition)) {
            if (layout[this.position.x][this.position.y].getPosition()==null)
                enemies.remove(layout[this.position.x][this.position.y]);
            layout[this.position.x][this.position.y] = this;
            layout[originalPosition.x][originalPosition.y] = new EmptyTile(originalPosition);
        }
        return output;
    }

    protected String moveLeft(Tile[][] layout) {
        try {
            return this.interact(layout[this.position.x - 1][this.position.y]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return ""; //Do nothing
        }
    }

    protected String moveRight(Tile[][] layout) {
        try {
            return this.interact(layout[this.position.x + 1][this.position.y]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return ""; //Do nothing
        }
    }

    protected String moveUp(Tile[][] layout) {
        try {
            return this.interact(layout[this.position.x][this.position.y - 1]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return ""; //Do nothing
        }
    }

    protected String moveDown(Tile[][] layout) {
        try {
            return this.interact(layout[this.position.x][this.position.y + 1]);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            return ""; //Do nothing
        }
    }

    public String interact(Tile tile) {
        return tile.accept(this);
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String visit(Enemy enemy) {
        return this.engage(enemy);
    }

    @Override
    public String visit(EmptyTile emptyTile) {
        this.switchPosition(emptyTile);
        return "";
    }

    @Override
    public String engage(Enemy enemy) {
        String combatResult = ANSIColors.GREEN.value() + this.name + " engaged in combat with " + enemy.getName() + ANSIColors.RESET.value()
                + "\n" + ANSIColors.BRIGHT_BLUE.value() + this.describe() + ANSIColors.RESET.value()
                + "\n" + ANSIColors.BLUE.value() + enemy.describe() + ANSIColors.RESET.value();
        Result attackResult = this.attack();
        int attackRoll = attackResult.getDiceRoll();
        combatResult += "\n" + attackResult.getOutput();
        Result defenseResult = enemy.defend();
        int defenseRoll = defenseResult.getDiceRoll();
        combatResult += "\n" + defenseResult.getOutput();
        int damage = attackRoll - defenseRoll;
        combatResult += "\n" + ANSIColors.BOLD.value() + this.name + " dealt " + Math.max(damage, 0) + " damage to " + enemy.getName() + ANSIColors.RESET.value();
        if (damage > 0)
            enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
        if (enemy.getCurrentHealth() <= 0) {
            combatResult += "\n" + this.kill(enemy);
            this.setPosition(enemy.getPosition());
            enemy.setPosition(null);
        }
        return combatResult;
    }

    protected String kill(Enemy enemy) {
        return enemy.getName() + this.gainExp(enemy.getExperienceValue());
    }

    public void die() {
        this.symbol = PlayerStatus.DEAD.getPlayerStatus();
    }

    private String gainExp(Integer experienceValue) {
        String output = " died. " + this.getName() + " gained " + experienceValue + " experience.";
        experience += experienceValue;
        while (experience >= experienceThreshold)
            output += "\n" + this.levelUp();
        return output;
    }

    protected String levelUp() {
        experience -= 50 * level;
        experienceThreshold += 50;
        level++;
        healthPool += 10 * level;
        currentHealth = healthPool;
        attack += 4 * level;
        defense += level;
        return ANSIColors.BRIGHT_YELLOW.value() + name + " reached level " + level + ": ";
    }

    protected List<Enemy> getAllEnemiesInRange(List<Enemy> enemies, int range) {
        List<Enemy> enemiesInRange = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (this.range(enemy) <= range)
                enemiesInRange.add(enemy);
        }
        return enemiesInRange;
    }

    protected Enemy chooseRandomEnemy(List<Enemy> enemies, int range) {
        List<Enemy> enemiesInRange = getAllEnemiesInRange(enemies, range);
        if (!enemiesInRange.isEmpty()) {
            int random = (int) (Math.random() * enemiesInRange.size());
            return enemiesInRange.get(random);
        }
        return null;
    }

    protected Enemy getClosestEnemyInRange(List<Enemy> enemies) {
        Enemy closestEnemy = null;
        double closestRange = Double.MAX_VALUE;
        for (Enemy enemy : enemies) {
            double currentRange = this.range(enemy);
            if (currentRange <= closestRange) {
                closestRange = currentRange;
                closestEnemy = enemy;
            }
        }
        return closestEnemy;
    }

    public PlayerStatus getPlayerStatus() {
        if (symbol == '@')
            return PlayerStatus.ALIVE;
        return PlayerStatus.DEAD;
    }

    public String toString() {
        return ANSIColors.GREEN.value() + symbol + ANSIColors.RESET.value();
    }
}
