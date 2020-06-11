package Model.UnitPackage.EnemyPackage;

import Model.ANSIColors;
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

    public abstract String onEnemyTurn(Tile[][] layout, Player player);

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public String engage(Player player) {
        String combatResult = ANSIColors.RED + this.name + " engaged in combat with " + player.getName() + ANSIColors.RESET
                + "\n" + ANSIColors.BLUE + this.describe() + ANSIColors.RESET
                + "\n" + ANSIColors.BRIGHT_BLUE + player.describe() + ANSIColors.RESET;
        Result attackResult = this.attack();
        int attackRoll = attackResult.getDiceRoll();
        combatResult += "\n" + attackResult.getOutput();
        Result defenseResult = player.defend();
        int defenseRoll = defenseResult.getDiceRoll();
        combatResult += "\n" + defenseResult.getOutput();
        int damage = attackRoll - defenseRoll;
        combatResult += "\n" + ANSIColors.BOLD + this.name + " dealt " + Math.max(damage, 0) + " damage  to " + player.getName() + ANSIColors.RESET;
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
