package Model.UnitPackage.EnemyPackage;

import java.awt.*;

public class Trap extends Enemy {
    private Integer visibilityTime;
    private Integer invisibilityTime;
    private Integer tickCount;
    private Boolean visible;

    public Trap(Traps trap, Point position) {
        this.name = trap.name;
        this.symbol = trap.symbol;
        this.healthPool = trap.healthPool;
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
        //returns full information of the current unit (don’t forget to
        //override this method in each subclass). Use it to print the information of each unit during
        //combat / on player’s turn.
        // You can override the Trap :: toString() method so it returns different characters depending
        //on its visibility state.
        throw new UnsupportedOperationException();
    }
}
