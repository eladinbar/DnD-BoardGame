package Model.UnitPackage.EnemyPackage;

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
    public void onEnemyTurn(Tile[][] layout, Player player) {
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
                     this.moveLeft(layout);
                else
                     this.moveRight(layout);
            }
            else
                if (dy > 0)
                    this.moveDown(layout);
                else
                    this.moveUp(layout);
        }
        else
            this.randomMovement(layout);
    }

    private void randomMovement(Tile[][] layout) {
        int rand = (int)Math.random()*5;
        switch(rand) {
            case 0:
                break; //Do nothing
            case 1:
                this.moveLeft(layout);
                break;
            case 2:
                this.moveRight(layout);
                break;
            case 3:
                this.moveUp(layout);
                break;
            case 4:
                this.moveDown(layout);
                break;
        }
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%21s", "Experience Value: ") + experienceValue + String.format("%17s", "Vision Range: ") + visionRange;
        //returns full information of the current unit.
        //Use it to print the information of each unit during combat / on player’s turn.
    }
}
