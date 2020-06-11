package Model.UnitPackage.EnemyPackage;

import Model.ANSIColors;
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
        if (this.range(player) < visionRange) {
            int dx, dy;
            if (combatTicks == abilityFrequency) {
                combatTicks = 0;
                try {
                    this.castAbility(layout, player);
                }
                catch(Exception ex) {

                }
//              - The boss cast the ability: shooting at the player for an amount that equals to the boss 's
//                attack points if the player is within vision range (the player will try to defend himself).
            }
            else {
                combatTicks++;
                dx = this.position.x - player.getPosition().x;
                dy = this.position.y - player.getPosition().y;
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0)
                        this.moveLeft(layout);
                    else
                        this.moveRight(layout);
                }
                else {
                    if (dy > 0)
                        this.moveUp(layout);
                    else
                        this.moveDown(layout);
                }
            }
        }
        else {
            combatTicks = 0;
            this.randomMovement(layout);
        }
    }

    @Override
    public void onGameTick() {

    }

    @Override
    public String castAbility(Tile[][] layout, Player player) throws Exception {
        //Depends on ability?
        return "";
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%12s", "Attack: ") + attack + String.format("%13s", "Defense: ")
                + defense + String.format("%20s", "Experience Value: ") + experienceValue + String.format("%17s", "Vision Range: ") + visionRange;
        //returns full information of the current unit.
        //Use it to print the information of each unit during combat / on player’s turn.
    }

    @Override
    public String toString() {
        return ANSIColors.MAGENTA + "" + symbol + ANSIColors.RESET;
    }
}
