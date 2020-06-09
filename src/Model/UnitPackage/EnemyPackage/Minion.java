package Model.UnitPackage.EnemyPackage;

import Model.UnitPackage.EnemyPackage.Monster;

import java.awt.*;

public class Minion extends Monster {

    public Minion(Point position, Minions minion) {
        super(position);
        this.name = minion.name;
        this.symbol = minion.symbol;
        this.healthPool = minion.healthPool;
        this.currentHealth = healthPool;
        this.attack = minion.attack;
        this.defense = minion.defense;
        this.visionRange = minion.visionRange;
        this.experienceValue = minion.experienceValue;
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%21s", "Experience Value: ") + experienceValue + String.format("%17s", "Vision Range: ") + visionRange;
        //returns full information of the current unit.
        //Use it to print the information of each unit during combat / on player’s turn.
    }
}
