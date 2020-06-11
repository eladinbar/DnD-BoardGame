package Model.UnitPackage.EnemyPackage;

import Model.ANSIColors;
import Model.TilePackage.Tile;
import Model.UnitPackage.PlayerPackage.Player;

import java.awt.Point;

public class Minion extends Monster {

    public Minion(Point position, Minions minion) {
        super(position);
        this.name = minion.name;
        this.symbol = minion.symbol;
        this.healthPool = minion.healthPool;
        this.currentHealth = healthPool;
        this.attack = minion.attack;
        this.defense = minion.defense;
        this.visionRange = minion.visionRange;
        this.experienceValue = minion.experienceValue;
    }

    @Override
    public void onGameTick() {

    }

    @Override
    public String onEnemyTurn(Tile[][] layout, Player player) {
//        The monster will attempt to traverse around the board.
//        – Monsters can move 1 step in the following directions: Up/Down/Left/Right, and may chase
//        the player if the player is within its vision range.
//        – Movement rules described as follows:
        int dx; int dy;
        if (this.range(player) < visionRange) {
            dx = this.position.x - player.getPosition().x;
            dy = this.position.y - player.getPosition().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0)
                     return this.moveLeft(layout);
                else
                     return this.moveRight(layout);
            }
            else
                if (dy > 0)
                    return this.moveDown(layout);
                else
                    return this.moveUp(layout);
        }
        else
            return this.randomMovement(layout);
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%21s", "Experience Value: ") + experienceValue + String.format("%17s", "Vision Range: ") + visionRange;
        //returns full information of the current unit.
    }

    @Override
    public String toString() {
        return ANSIColors.RED + "" + symbol + ANSIColors.RESET;
    }
}
