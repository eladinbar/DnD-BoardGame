package Model.UnitPackage.PlayerPackage;

import Model.ANSIColors;
import Model.Result;
import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.Enemy;

import java.awt.Point;
import java.util.List;

public class Hunter extends Player {
    private Integer range;
    private Integer arrowsCount;
    private Integer ticksCount;

    public Hunter(Point position, Hunters hunter) {
        super(position);
        this.name = hunter.name;
        this.healthPool = hunter.healthPool;
        this.currentHealth = healthPool;
        this.attack = hunter.attack;
        this.defense = hunter.defense;
        this.range = hunter.range;
        this.arrowsCount = 10;
        this.ticksCount = 0;
        //Using arrows as resource. Starting amount of arrows in quiver is equal to (10 × level).
    }

    @Override
    public String levelUp() {
        String output = super.levelUp();
        arrowsCount += 10 * level;
        attack += 2 * level;
        defense += level;
        return output + "+" + 10*level + " Health, " + "+" + 6*level + "Attack, " + "+" + 2*level + "Defense, " + "+" + 10*level + " Arrows " + ANSIColors.RESET;
    }

    public void onGameTick() {
        if (ticksCount == 10) {
            arrowsCount += level;
            ticksCount = 0;
        }
        else
            ticksCount++;
    }

    @Override
    public String castAbility(Tile[][] layout, List<Enemy> enemies) throws Exception {
        //Special ability: Shoot, hits the closest enemy for an amount that equals to the hunter’s attack points at
        //the cost of an arrow.
        if (arrowsCount == 0)
            throw new Exception(name + " tried to cast Shoot but does not have enough arrows. " + (10-ticksCount) + " more turns are left until an arrow is replenished.");
        else if (getAllEnemiesInRange(enemies, range).isEmpty())
            throw new Exception(name + " Tried to shoot but there were no enemies in range.");
        else {
            String combatResult = "";
            List<Enemy> enemiesInRange = getAllEnemiesInRange(enemies, range);
            Enemy closestEnemy = getClosestEnemyInRange(enemiesInRange, range);
            if (closestEnemy!=null) {
                combatResult = ANSIColors.CYAN + name + " fired an arrow at " + closestEnemy.getName() + "." + ANSIColors.RESET;
                Result defenseResult = closestEnemy.defend();
                int defenseRoll = defenseResult.getDiceRoll();
                combatResult += "\n" + defenseResult.getOutput();
                int damage = attack - defenseRoll;
                combatResult += "\n" + ANSIColors.BOLD + this.name + " hit " + closestEnemy.getName() + " for " + Math.max(damage, 0) + " ability damage." + ANSIColors.RESET;
                if (damage > 0)
                    closestEnemy.setCurrentHealth(closestEnemy.getCurrentHealth() - damage);
                if (closestEnemy.getCurrentHealth() <= 0)
                    this.kill(closestEnemy);
                arrowsCount--;
            }
            //Deal damage equal to attack points to the closest enemy within range (The enemy will try to
            //defend itself).
            return combatResult;
        }
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%14s", "Level: ") + level + String.format("%16s", "Experience: ") + experience+"/"+experienceThreshold +
                String.format("%15s", "Arrows: ") + arrowsCount + String.format("%14s", "Range: ") + range;
        //returns full information on the current unit.
    }
}
