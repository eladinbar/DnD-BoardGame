package Model.UnitPackage.PlayerPackage;

import Model.ANSIColors;
import Model.Result;
import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.Enemy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Warrior extends Player {
    private Integer abilityCooldown;
    private Integer remainingCooldown;
    private final int AVENGERS_SHIELD_RANGE = 3;

    public Warrior(Point position, Warriors warrior) {
        super(position);
        this.name = warrior.name;
        this.healthPool = warrior.healthPool;
        this.currentHealth = healthPool;
        this.abilityCooldown = warrior.abilityCooldown;
        this.remainingCooldown = 0;
        this.attack = warrior.attack;
        this.defense = warrior.defense;
        //The warrior’s ability has a cooldown, meaning it can only be used once every ability cooldown
        //game ticks.
    }

    @Override
    public String castAbility(Tile[][] layout, List<Enemy> enemies) throws Exception {
        if (remainingCooldown>0)
            throw new Exception(name + " tried to cast Avenger's Shield but failed. Remaining cooldown is: " + remainingCooldown);
        else {
            String combatResult = ANSIColors.CYAN.value() + name + " used Avenger's Shield, healing for " + 10*defense + "." + ANSIColors.RESET.value();
            remainingCooldown = abilityCooldown;
            currentHealth = Math.min(currentHealth + (10*defense), healthPool);
            Enemy enemy = chooseRandomEnemy(enemies, AVENGERS_SHIELD_RANGE);
            if (enemy!=null) {
                Result defenseResult = enemy.defend();
                int defenseRoll = defenseResult.getDiceRoll();
                combatResult += "\n" + defenseResult.getOutput();
                int damage = healthPool/10 - defenseRoll;
                combatResult += "\n" + ANSIColors.BOLD.value() + this.name + " hit " + enemy.getName() + " for " + Math.max(damage, 0) + " ability damage." + ANSIColors.RESET.value();
                if (damage > 0)
                    enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
                if (enemy.getCurrentHealth() <= 0) {
                    combatResult += "\n" + this.kill(enemy);
                    layout[enemy.getPosition().x][enemy.getPosition().y] = new EmptyTile(enemy.getPosition());
                    enemies.remove(enemy);
                }
            }
            //Special ability: Avenger’s Shield, randomly hits one enemy within range < 3 for an amount that
            //equals to 10% of the warrior’s max health and heals the warrior for an amount equal to (10×defense)
            //(but will not exceed the total amount of health pool).
            return combatResult;
        }
    }

    public void onGameTick() {
        setRemainingCooldown(remainingCooldown-1);
    }

    @Override
    protected String levelUp() {
        String output = super.levelUp();
        remainingCooldown = 0;
        healthPool += 5 * level;
        currentHealth = healthPool;
        attack += 2 * level;
        defense += level;
        return output + "+" + 15*level + " Health, " + "+" + 6*level + " Attack, " + "+" + 2*level + " Defense" + ANSIColors.RESET.value();
    }

    public void setRemainingCooldown(Integer remainingCooldown) {
        this.remainingCooldown = Math.max(remainingCooldown, 0);
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%14s", "Level: ") + level + String.format("%16s", "Experience: ") + experience+"/"+experienceThreshold +
                String.format("%15s", "Cooldown: ") + remainingCooldown+"/"+abilityCooldown;
        //returns full information on the current unit.
    }
}
