package Model.UnitPackage.EnemyPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Visitor;
import Model.TilePackage.Wall;
import Model.UnitPackage.PlayerPackage.Player;
import Model.UnitPackage.Unit;

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

    public void onEnemyTurn(Player player) {
//        The monster will attempt to traverse around the board.
//        – Monsters can move 1 step in the following directions: Up/Down/Left/Right, and may chase
//        the player if the player is within its vision range.
//        – Movement rules described as follows:
        if (this.range(player) < visionRange) {
            this.position.x = this.position.x - player.position.x;
            this.position.y = this.position.y - player.position.y;
            if (Math.abs(this.position.x) > Math.abs(this.position.y)) {
              if (this.position.x > 0)
                  Move left
              else
                  Move right
              }
        }
        else
            if (this.position.y > 0)
                Move up
            else
                Move down
    }
    else
        Perform a random movement action: left, right, up, down or stay at the same place.
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%21s", "Experience Value: ") + experienceValue + String.format("%17s", "Vision Range: ") + visionRange;
        //returns full information of the current unit.
        //Use it to print the information of each unit during combat / on player’s turn.
    }

    @Override
    public void visit(Unit unit) {

    }

    @Override
    public void visit(Wall wall) {

    }

    @Override
    public void visit(EmptyTile emptyTile) {

    }

    @Override
    public void accept(Visitor visitor) {

    }
}
