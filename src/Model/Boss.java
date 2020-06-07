package Model;
//– The Mountain
//– Queen Cersei
//– Night’s King
public abstract class Boss extends Monster implements HeroicUnit {
    Integer visionRange;
    Integer abilityFrequency;
    Integer combatTicks;

    public void onEnemyTurn() {
//        ∗ The boss will attempt to traverse around the board.
//        ∗ Boss can move 1 step in the following directions: Up/Down/Left/Right, and may chase the
//        player if the player is within its vision range.
//        ∗ Movement rules are mostly similar to the monster’s rules, except:
//        if range(monster, player) < vision range then
//          if (combatTicks == abilityFrequency) {
//           - combatTicks = 0
//           - The boss cast the ability: shooting at the player for an amount that equals to the boss's
//             attack points if the player is within vision range (the player will try to defend himself).
//          else {
//              combatTicks++;
//              dx = enemyX − playerX
//              dy = enemyY − playerY
//              if |dx| > |dy| then
//                if dx > 0 then
//                  Move left
//                else
//                  Move right
//              else
//                if dy > 0 then
//                  Move up
//                else
//                  Move down
//          }
//        }
//        else
//          combatTicks = 0
//        Perform a random movement action: left, right, up, down or stay in the same place.
    }
}