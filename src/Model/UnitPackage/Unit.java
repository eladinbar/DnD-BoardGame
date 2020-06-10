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
    public String ANSI_RESET = "\u001B[0m";

    public Unit(Point position) {
        super(position);
    }

    public String getName() {
        return name;
    }

    public abstract String describe();

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = Math.min(currentHealth, healthPool);
    }

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
        String output = this.name + " rolled " + defense + " defense points.";
        Result result = new Result(defenseRoll, output);
        return result;
    }
}
