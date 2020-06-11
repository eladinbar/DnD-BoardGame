package Model.UnitPackage.EnemyPackage;

import Model.Result;
import Model.TilePackage.Tile;
import Model.UnitPackage.PlayerPackage.Player;
import Model.UnitPackage.Unit;
import Model.UnitPackage.Visitor;

import java.awt.Point;

public abstract class Enemy extends Unit {
    protected Integer experienceValue;

    public Enemy(Point position) {
        super(position);
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
        combatResult += "\n" + this.name + " dealt " + Math.max(damage, 0) + " damage  to " + player.getName();
        if (damage > 0)
            player.setCurrentHealth(player.getCurrentHealth() - damage);
//        if (player.getCurrentHealth() <= 0) {
//            player.die(); //Observer pending
//        }
        return combatResult;
    }

    public Integer getExperienceValue() {
        return experienceValue;
    }

    @Override
    public void onGameTick() {

    }
}
