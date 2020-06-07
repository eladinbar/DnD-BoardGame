package Model;

public class Trap extends Enemy {
    private Integer visibilityTime;
    private Integer invisibilityTime;
    private Integer tickCount;
    private Boolean visible;

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
