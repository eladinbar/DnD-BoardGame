package Model.UnitPackage.EnemyPackage;

import Model.ANSIColors;
import Model.TilePackage.EmptyTile;
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
        Point originalPosition = this.position;
        String output = "";
        if (this.range(player) < visionRange) {
            dx = this.position.x - player.getPosition().x;
            dy = this.position.y - player.getPosition().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0)
                     output = this.moveLeft(layout);
                else
                     output = this.moveRight(layout);
            }
            else
                if (dy > 0)
                    output = this.moveUp(layout);
                else
                    output = this.moveDown(layout);
        }
        else
            output = this.randomMovement(layout);
        if (!this.position.equals(originalPosition)) {
            layout[this.position.x][this.position.y] = this;
            layout[originalPosition.x][originalPosition.y] = new EmptyTile(originalPosition);
        }
        return output;
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%21s", "Experience Value: ") + experienceValue + String.format("%17s", "Vision Range: ") + visionRange;
        //returns full information on the current unit.
    }

    @Override
    public String toString() {
        return ANSIColors.RED.value() + "" + symbol + ANSIColors.RESET.value();
    }
}
