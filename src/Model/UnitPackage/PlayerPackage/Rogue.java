package Model.UnitPackage.PlayerPackage;

import Model.ANSIColors;
import Model.Result;
import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.Enemy;

import java.awt.Point;
import java.util.List;

public class Rogue extends Player {
    private Integer energyCost;
    private Integer currentEnergy;
    private final int FAN_OF_KNIVES_RANGE = 2;

    public Rogue(Point position, Rogues rogue) {
        super(position);
        this.name = rogue.name;
        this.healthPool = rogue.healthPool;
        this.currentHealth = healthPool;
        this.attack = rogue.attack;
        this.defense = rogue.defense;
        this.energyCost = rogue.energyCost;
        this.currentEnergy = energyCost;
        //Using energy as resource. Starting energy equals to the rogue’s maximum energy which is 100.
    }

    @Override
    public String levelUp() {
        String output = super.levelUp();
        currentEnergy = 100;
        attack += 3 * level;
        return output + "+" + 10*level + " Health, " + "+" + 7*level + " Attack, " + "+" + level + " Defense" + ANSIColors.RESET.value();
    }

    public void onGameTick() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    public String castAbility(Tile[][] layout, List<Enemy> enemies) throws Exception {
        if (currentEnergy < energyCost) {
            throw new Exception(name + " tried to cast Fan of Knives but does not have enough energy. " + (energyCost-currentEnergy) + " more energy is required to cast the ability.");
        }
        else {
            String combatResult = ANSIColors.CYAN.value() + name + " cast Fan of Knives." + ANSIColors.RESET.value();
            currentEnergy -= energyCost;
            List<Enemy> enemiesInRange = getAllEnemiesInRange(enemies, FAN_OF_KNIVES_RANGE);
            for (Enemy enemy : enemiesInRange) {
                Result defenseResult = enemy.defend();
                int defenseRoll = defenseResult.getDiceRoll();
                combatResult += "\n" + defenseResult.getOutput();
                int damage = attack - defenseRoll;
                combatResult += "\n" + ANSIColors.BOLD.value() + this.name + " hit " + enemy.getName() + " for " + Math.max(damage, 0) + " ability damage." + ANSIColors.RESET.value();
                if (damage > 0)
                    enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
                if (enemy.getCurrentHealth() <= 0)
                    this.kill(enemy);
            }
            //For each enemy within range < 2, deal damage equal to the rogue’s
            //attack points (each enemy will try to defend itself).
            return combatResult;
        }
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%14s", "Level: ") + level + String.format("%16s", "Experience: ") + experience+"/"+experienceThreshold +
                String.format("%15s", "Energy: ") + currentEnergy+"/"+energyCost;
        //returns full information on the current unit.
    }
}
