package Model;

public class Trap extends Enemy {
    Integer visibilityTime;
    Integer invisibilityTime;
    Integer tickCount;
    Boolean visible;

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
}
