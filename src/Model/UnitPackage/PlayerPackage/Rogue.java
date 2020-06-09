package Model.UnitPackage.PlayerPackage;

import java.awt.*;

public class Rogue extends Player {
    private Integer energyCost;
    private Integer currentEnergy;

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
    public void levelUp() {
        super.levelUp();
        currentEnergy = 100;
        attack += 3 * level;
    }

    public void onGameTick() {
        currentEnergy = Math.min(currentEnergy + 10, 100);
    }

    @Override
    public void castAbility(/*insert parameters*/) throws Exception {
        if (currentEnergy < energyCost) {
            throw new Exception(/*insert appropriate message*/);
        }
        else {
            currentEnergy -= energyCost;
            //For each enemy within range < 2, deal damage (reduce health value) equals to the rogue’s
            //attack points (each enemy will try to defend itself).
        }
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%14s", "Level: ") + level + String.format("%16s", "Experience: ") + experience+"/"+experienceThreshold +
                String.format("%15s", "Energy: ") + currentEnergy+"/"+energyCost;
        //returns full information on the current unit.
        //Use it to print the information of each unit during combat / on player turn.
    }
}
