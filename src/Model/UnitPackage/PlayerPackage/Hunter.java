package Model.UnitPackage.PlayerPackage;

import Model.ANSIColors;
import Model.Result;
import Model.TilePackage.EmptyTile;
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
    public String castAbility(Tile[][] layout, List<Enemy> enemies) throws Exception {
        //Special ability: Shoot, hits the closest enemy for an amount that equals to the hunter’s attack points at
        //the cost of an arrow.
        if (arrowsCount == 0)
            throw new Exception(name + " tried to cast Shoot but does not have enough arrows. " + (10-ticksCount) + " more turns are left until an arrow is replenished.");

        List<Enemy> enemiesInRange = getAllEnemiesInRange(enemies, range);
        if (enemiesInRange.isEmpty())
            throw new Exception(name + " tried to shoot but there were no enemies in range.");

        String combatResult = "";
        Enemy closestEnemy = getClosestEnemyInRange(enemiesInRange);
        if (closestEnemy!=null) {
            combatResult = ANSIColors.CYAN.value() + name + " fired an arrow at " + closestEnemy.getName() + "." + ANSIColors.RESET.value();
            Result defenseResult = closestEnemy.defend();
            int defenseRoll = defenseResult.getDiceRoll();
            combatResult += "\n" + defenseResult.getOutput();
            int damage = attack - defenseRoll;
            combatResult += "\n" + ANSIColors.BOLD.value() + this.name + " hit " + closestEnemy.getName() + " for " + Math.max(damage, 0) + " ability damage." + ANSIColors.RESET.value();
            if (damage > 0)
                closestEnemy.setCurrentHealth(closestEnemy.getCurrentHealth() - damage);
            if (closestEnemy.getCurrentHealth() <= 0) {
                combatResult += "\n" + this.kill(closestEnemy);
                layout[closestEnemy.getPosition().x][closestEnemy.getPosition().y] = new EmptyTile(closestEnemy.getPosition());
                enemies.remove(closestEnemy);
            }
            arrowsCount--;
        }
        //Deal damage equal to attack points to the closest enemy within range (The enemy will try to
        //defend itself).
        return combatResult;
    }

    @Override
    public void onGameTick() {
        if (ticksCount == 10) {
            arrowsCount += level;
            ticksCount = 0;
        }
        else
            ticksCount++;
    }

    @Override
    protected String levelUp() {
        String output = super.levelUp();
        arrowsCount += 10 * level;
        attack += 2 * level;
        defense += level;
        return output + "+" + 10*level + " Health, " + "+" + 6*level + " Attack, " + "+" + 2*level + " Defense, " + "+" + 10*level + " Arrows " + ANSIColors.RESET.value();
    }

    public void setArrowsCount(Integer arrowsCount) {
        this.arrowsCount = Math.max(arrowsCount, 0);
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%14s", "Level: ") + level + String.format("%16s", "Experience: ") + experience+"/"+experienceThreshold +
                String.format("%15s", "Arrows: ") + arrowsCount + String.format("%14s", "Range: ") + range;
        //returns full information on the current unit.
    }
}
