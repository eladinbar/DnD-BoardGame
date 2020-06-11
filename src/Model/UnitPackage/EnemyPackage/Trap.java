package Model.UnitPackage.EnemyPackage;

import Model.ANSIColors;
import Model.TilePackage.Tile;
import Model.UnitPackage.PlayerPackage.Player;

import java.awt.*;

public class Trap extends Enemy {
    private Integer visibilityTime;
    private Integer invisibilityTime;
    private Integer ticksCount;
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
        this.ticksCount = 0;
        this.visible = true;
    }

    @Override
    public void onEnemyTurn(Tile[][] layout, Player player) {
//        – A trap can’t move (unlike monsters), but updates its state (visibility) on each turn.
//        – After visibility time game ticks, the trap will turn invisible.
//        – The trap becomes visible invisibility time game ticks afterwards.
//        – The trap may attack the player if the range (trap, player) < 2.
//        – The trap’s state will be updated on each turn as follows:
        onGameTick();
        if (this.range(player) < 2)
          this.engage(player);
    }

    @Override
    public void onGameTick() {
        visible = ticksCount < visibilityTime;
        if (ticksCount == (visibilityTime + invisibilityTime))
            ticksCount = 0;
        else
            ticksCount++;
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
    public String toString() {
        if (visible)
            return ANSIColors.BRIGHT_RED + "" + symbol + ANSIColors.RESET;
        else
            return ".";
    }
}
