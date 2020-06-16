package Model.UnitPackage.EnemyPackage;

import Model.ANSIColors;
import Model.Result;
import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.UnitPackage.HeroicUnit;
import Model.UnitPackage.PlayerPackage.Player;

import java.awt.Point;

public class Boss extends Monster implements HeroicUnit {
    protected Integer abilityFrequency;
    protected Integer combatTicks;
    protected Integer abilityRange;
    protected Integer abilityDamage;
    protected String abilityName;

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
        this.abilityRange = boss.abilityRange;
        this.abilityDamage = boss.abilityDamage;
        this.abilityName = boss.abilityName;
        this.combatTicks = 0;
    }

    @Override
    public String onEnemyTurn(Tile[][] layout, Player player) {
//        ∗ The boss will attempt to traverse around the board.
//        ∗ Boss can move 1 step in the following directions: Up/Down/Left/Right, and may chase the
//          player if the player is within its vision range.
//        ∗ Movement rules are mostly similar to the monster’s rules, except:
        Point originalPosition = this.position;
        String output="";
        double rangeFromPlayer = this.range(player);
        if (combatTicks == abilityFrequency & rangeFromPlayer <= abilityRange) {
            combatTicks = 0;
            try {
                output = this.castAbility(layout, player);
            }
            catch(Exception ex) {
                return output;
            }
//          - The boss cast the ability: shooting at the player for an amount that equals to the boss's
//            ability damage points if the player is within ability range (the player will try to defend himself).
        }
        else if (rangeFromPlayer <= visionRange) {
            int dx, dy;
            if (combatTicks!=abilityFrequency)
                combatTicks++;
            dx = this.position.x - player.getPosition().x;
            dy = this.position.y - player.getPosition().y;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0)
                    output = this.moveLeft(layout);
                else
                    output = this.moveRight(layout);
            }
            else {
                if (dy > 0)
                    output = this.moveUp(layout);
                else
                    output = this.moveDown(layout);
            }
        }
        else {
            combatTicks = 0;
            output = this.randomMovement(layout);
        }
        if (!this.position.equals(originalPosition)) {
            layout[this.position.x][this.position.y] = this;
            layout[originalPosition.x][originalPosition.y] = new EmptyTile(originalPosition);
        }
        return output;
    }

    @Override
    public void onGameTick() {
        //Combat ticks are tied to on enemy turn functionality
    }

    @Override
    public String castAbility(Tile[][] layout, Player player) throws Exception {
        String combatResult = ANSIColors.BRIGHT_MAGENTA.value() + name + " cast " + abilityName + " at " + player.getName() + "." + ANSIColors.RESET.value();
        Result defenseResult = player.defend();
        int defenseRoll = defenseResult.getDiceRoll();
        combatResult += "\n" + defenseResult.getOutput();
        int damage = abilityDamage - defenseRoll;
        combatResult += "\n" + ANSIColors.BOLD.value() + this.name + " hit " + player.getName() + " for " + Math.max(damage, 0) + " ability damage." + ANSIColors.RESET.value();
        if (damage > 0)
            player.setCurrentHealth(player.getCurrentHealth() - damage);
        if (player.getCurrentHealth() <= 0)
            player.die();
        return combatResult;
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%12s", "Attack: ") + attack + String.format("%13s", "Defense: ")
                + defense + String.format("%20s", "Experience Value: ") + experienceValue + String.format("%17s", "Vision Range: ") + visionRange;
        //returns full information on the current unit.
    }

    @Override
    public String toString() {
        return ANSIColors.MAGENTA.value() + symbol + ANSIColors.RESET.value();
    }
}
