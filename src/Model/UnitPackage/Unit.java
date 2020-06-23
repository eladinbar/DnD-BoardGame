package Model.UnitPackage;

import Model.Result;
import Model.TilePackage.Tile;

import java.awt.Point;

public abstract class Unit extends Tile implements Attacker, Combatant, Defender, TickListener {
    protected String name;
    protected Integer healthPool;
    protected Integer currentHealth;
    protected Integer attack;
    protected Integer defense;

    public Unit(Point position) {
        super(position);
    }

    public abstract String describe();

    @Override
    public Result attack() {
        int attackRoll = (int)(Math.random()*(attack)+1);
        String output = this.name + " rolled " + attackRoll + " attack points.";
        Result result = new Result(attackRoll, output);
        return result;
    }

    @Override
    public Result defend() {
        int defenseRoll = (int)(Math.random()*(defense)+1);
        String output = this.name + " rolled " + defenseRoll + " defense points.";
        Result result = new Result(defenseRoll, output);
        return result;
    }

    public String getName() {
        return name;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public Integer getHealthPool() {
        return healthPool;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = Math.max(Math.min(currentHealth, healthPool), 0);
    }
}
