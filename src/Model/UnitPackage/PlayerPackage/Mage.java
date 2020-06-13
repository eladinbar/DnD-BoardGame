package Model.UnitPackage.PlayerPackage;

import Model.ANSIColors;
import Model.Result;
import Model.TilePackage.EmptyTile;
import Model.TilePackage.Tile;
import Model.UnitPackage.EnemyPackage.Enemy;

import java.awt.Point;
import java.util.List;

public class Mage extends Player {
    private Integer manaPool;
    private Integer currentMana;
    private Integer manaCost;
    private Integer spellPower;
    private Integer hitsCount;
    private Integer abilityRange;

    public Mage(Point position, Mages mage) {
        super(position);
        this.name = mage.name;
        this.healthPool = mage.healthPool;
        this.currentHealth = healthPool;
        this.attack = mage.attack;
        this.defense = mage.defense;
        this.manaPool = mage.manaPool;
        this.currentMana = manaPool/4;
        this.manaCost = mage.manaCost;
        this.spellPower = mage.spellPower;
        this.hitsCount = mage.hitsCount;
        this.abilityRange = mage.abilityRange;
    }

    @Override
    public String castAbility(Tile[][] layout, List<Enemy> enemies) throws Exception {
        //Special ability: Blizzard, randomly hit enemies within range for an amount equals to the mage’s
        //spell power at the cost of mana.
        if (currentMana < manaCost)
            throw new Exception(name + " tried to case Blizzard but does not have enough mana. " + (manaCost-currentMana) + " more mana is required to cast the ability.");
        else {
            currentMana -= manaCost;
            Integer hits = 0;
            List<Enemy> enemiesInRange = getAllEnemiesInRange(enemies, abilityRange);
            String combatResult = ANSIColors.CYAN.value() + name + " cast Blizzard." + ANSIColors.RESET.value();
            while (hits < hitsCount & !enemiesInRange.isEmpty()) {
                //Select a random enemy within range
                Enemy enemy = chooseRandomEnemy(enemiesInRange, abilityRange);
                //Deal damage to the chosen enemy for an amount equal to spell power
                //(each enemy may try to defend itself).
                if (enemy!=null) {
                    Result defenseResult = enemy.defend();
                    int defenseRoll = defenseResult.getDiceRoll();
                    combatResult += "\n" + defenseResult.getOutput();
                    int damage = spellPower - defenseRoll;
                    combatResult += "\n" + ANSIColors.BOLD.value() + this.name + " hit " + enemy.getName() + " for " + Math.max(damage, 0) + " ability damage." + ANSIColors.RESET.value();
                    if (damage > 0)
                        enemy.setCurrentHealth(enemy.getCurrentHealth() - damage);
                    if (enemy.getCurrentHealth() <= 0) {
                        combatResult += "\n" + this.kill(enemy);
                        layout[enemy.getPosition().x][enemy.getPosition().y] = new EmptyTile(enemy.getPosition());
                        enemiesInRange.remove(enemy);
                        enemies.remove(enemy);
                    }
                }
                hits++;
            }
            return combatResult;
        }
    }

    public void onGameTick() {
        this.setCurrentMana(currentMana + level);
    }

    @Override
    protected String levelUp() {
        String output = super.levelUp();
        manaPool += 25 * level;
        currentMana = Math.min(currentMana + manaPool/4, manaPool);
        spellPower += 10 * level;
        return output + "+" + 10*level + " Health, " + "+" + 25*level + " Mana, " + "+" + 4*level + " Attack, " + "+" + level + " Defense, " + "+" + 10*level + " Spellpower" + ANSIColors.RESET.value();
    }

    public void setCurrentMana(Integer currentMana) {
        this.currentMana = Math.min(currentMana, manaPool);
    }


    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%14s", "Level: ") + level + String.format("%17s", "Experience: ") + experience+"/"+experienceThreshold +
                String.format("%13s", "Mana: ") + currentMana+"/"+manaPool+" " + String.format("%16s", "Spell Power: ") + spellPower;
        //returns full information on the current unit.
    }
}
