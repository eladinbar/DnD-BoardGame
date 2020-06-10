package Model.UnitPackage.EnemyPackage;

import Model.Result;
import Model.TilePackage.Tile;
import Model.UnitPackage.PlayerPackage.Player;
import Model.UnitPackage.Unit;
import Model.UnitPackage.Visitor;

import java.awt.Point;

public abstract class Enemy extends Unit {
    protected Integer experienceValue;
    public final String ANSI_RED = "\u001B[31m";

    public Enemy(Point position) {
        super(position);
    }

    @Override
    public String describe() {
        //returns full information of the current unit.
        //Use it to print the information of each unit during combat / on player’s turn.
        throw new UnsupportedOperationException();
    }

    public abstract void onEnemyTurn(Tile[][] layout, Player player);

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String engage(Player player) {
        String combatResult = this.name + " engaged in combat with " + player.getName()
                + "\n" + this.describe() + "\n" + player.describe();
        Result attackResult = this.attack();
        int attackRoll = attackResult.getDiceRoll();
        combatResult += "\n" + attackResult.getOutput();
        Result defenseResult = player.defend();
        int defenseRoll = defenseResult.getDiceRoll();
        combatResult += "\n" + defenseResult.getOutput();
        int damage = attackRoll - defenseRoll;
        combatResult += "\n" + this.name + " dealt " + damage + " damage  to " + player.getName();
        if (damage > 0)
            player.setCurrentHealth(player.getCurrentHealth() - damage);
//        if (player.getCurrentHealth() <= 0) {
//            player.die(); //Observer pending
//        }
        return combatResult;
    }

    @Override
    public String engage(Enemy enemy) {
        return ""; //Do nothing
    }

    @Override
    public String toString() {
        return ANSI_RED + symbol + ANSI_RESET;
    }

    public Integer getExperienceValue() {
        return experienceValue;
    }
}
