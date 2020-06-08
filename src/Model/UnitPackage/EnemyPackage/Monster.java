package Model.UnitPackage.EnemyPackage;

public abstract class Monster extends Enemy {
    protected Integer visionRange;

    public void onEnemyTurn() {
//        The monster will attempt to traverse around the board.
//        – Monsters can move 1 step in the following directions: Up/Down/Left/Right, and may chase
//        the player if the player is within its vision range.
//        – Movement rules described as follows:
//        if (range(monster, player)) < visionRange {
//            dx = enemyX - playerX
//            dy = enemyY - playerY
//            if (Math.abs(dx)>Math.abs(dy)) {
//              if (dx > 0)
//                  Move left
//              else
//                  Move right
//              }
//        }
//        else
//            if (dy > 0)
//                Move up
//            else
//                Move down
//    }
//    else
//        Perform a random movement action: left, right, up, down or stay at the same place.
    }

    @Override
    public String describe() {
        //returns full information of the current unit (don’t forget to
        //override this method in each subclass). Use it to print the information of each unit during
        //combat / on player’s turn.
        throw new UnsupportedOperationException();
    }
}
