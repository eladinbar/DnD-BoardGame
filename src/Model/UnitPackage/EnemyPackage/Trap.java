package Model.UnitPackage.EnemyPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Visitor;
import Model.TilePackage.Wall;
import Model.UnitPackage.Unit;

import java.awt.*;

public class Trap extends Enemy {
    private Integer visibilityTime;
    private Integer invisibilityTime;
    private Integer tickCount;
    private Boolean visible;

    public Trap(Point position, Traps trap) {
        super(position);
        this.name = trap.name;
        this.symbol = trap.symbol;
        this.healthPool = trap.healthPool;
        this.currentHealth = healthPool;
        this.attack = trap.attack;
        this.defense = trap.defense;
        this.experienceValue = trap.experienceValue;
        this.visibilityTime = trap.visibilityTime;
        this.invisibilityTime = trap.invisibilityTime;
        this.tickCount = 0;
        this.visible = true;
    }

    public void onEnemyTurn() {
//        – A trap can’t move (unlike monsters), but updates its state (visibility) on each turn.
//        – After visibility time game ticks, the trap will turn invisible.
//        – The trap becomes visible invisibility time game ticks afterwards.
//        – The trap may attack the player if the range (trap, player) < 2.
//        – The trap’s state will be updated on each turn as follows:
//        visible = ticksCount < visibilityTime
//        if (ticks count == (visibilityTime + invisibilityTime))
//          ticks count = 0
//        else
//          ticksCount++;
//        if range(trap, player) < 2
//          attack(player)
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%21s", "Experience Value: ") + experienceValue;
        //returns full information of the current unit.
        //Use it to print the information of each unit during combat / on player’s turn.
        //You can override the Trap :: toString() method so it returns different characters depending
        //on its visibility state.
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
