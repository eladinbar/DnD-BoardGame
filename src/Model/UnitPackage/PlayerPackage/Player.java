package Model.UnitPackage.PlayerPackage;

import Controller.ActionListInput;
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

public abstract class Player extends Unit implements HeroicUnit, Visitor, TickListener {
    protected Integer experience;
    protected Integer level;
    protected Integer experienceThreshold;
    public final String TEXT_COLOR_GREEN = "\u001B[32m";

    public Player(Point position) {
        super(position);
        this.symbol = PlayerStatus.ALIVE.playerStatus;
        this.experience = 0;
        this.level = 1;
        this.experienceThreshold = 50 * level;
    }

    private void gainExp(Integer experienceValue) {
        if (experience + experienceValue >= experienceThreshold)
            this.levelUp();
        else
            experience += experienceValue;
    }

    public void levelUp() {
        experience -= 50 * level;
        level++;
        healthPool += 10 * level;
        currentHealth = healthPool;
        attack += 4 * level;
        defense += level;
    }

    public void onPlayerTurn(Tile[][] layout, ActionListInput action) {

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
    public String visit(Player player) {
        return ""; //Do nothing
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
        String combatResult = this.name + " engaged in combat with " + enemy.getName()
                                + "\n" + this.describe() + "\n" + enemy.describe();
        Result attackResult = this.attack();
        int attackRoll = attackResult.getDiceRoll();
        combatResult += "\n" + attackResult.getOutput();
        Result defenseResult = enemy.defend();
        int defenseRoll = defenseResult.getDiceRoll();
        combatResult += "\n" + defenseResult.getOutput();
        int damage = attackRoll - defenseRoll;
        combatResult += "\n" + this.name + " dealt " + damage + " damage  to " + enemy.getName();
        if (damage > 0)
            enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
        if (enemy.getCurrentHealth() <= 0) {
            this.gainExp(enemy.getExperienceValue());
            EmptyTile et = new EmptyTile(this.position);
            this.setPosition(enemy.getPosition());
            enemy.setPosition(null);
        }
        return combatResult;
    }

    @Override
    public String engage(Player player) {
        return ""; //Do nothing
    }

    public String toString() {
        return TEXT_COLOR_GREEN + symbol + ANSI_RESET;
    }
}
