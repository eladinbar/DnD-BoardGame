package Model.UnitPackage.EnemyPackage;

import Model.TilePackage.Tile;
import Model.UnitPackage.HeroicUnit;
import Model.UnitPackage.PlayerPackage.Player;

import java.awt.Point;

//– The Mountain
//– Queen Cersei
//– Night King
public class Boss extends Monster implements HeroicUnit {
    protected Integer abilityFrequency;
    protected Integer combatTicks;

    public Boss(Point position, Bosses boss) {
        super(position);
        this.name = boss.name;
        this.symbol = boss.symbol;
        this.healthPool = boss.healthPool;
        this.currentHealth = healthPool;
        this.attack = boss.attack;
        this.defense = boss.defense;
        this.visionRange = boss.visionRange;
        this.experienceValue = boss.experienceValue;
        this.abilityFrequency = boss.abilityFrequency;
        this.combatTicks = 0;
    }

    @Override
    public void onEnemyTurn(Tile[][] layout, Player player) {
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

    @Override
    public void onGameTick() {

    }

    @Override
    public void castAbility(/*insert parameters*/) throws Exception {

    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%12s", "Attack: ") + attack + String.format("%13s", "Defense: ")
                + defense + String.format("%20s", "Experience Value: ") + experienceValue + String.format("%17s", "Vision Range: ") + visionRange;
        //returns full information of the current unit.
        //Use it to print the information of each unit during combat / on player’s turn.
    }
}
