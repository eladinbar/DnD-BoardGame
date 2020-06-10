package Model.UnitPackage.PlayerPackage;

import java.awt.Point;

public class Hunter extends Player {
    //Special ability: Shoot, hits the closest enemy for an amount equals to the hunter’s attack points at
    //the cost of an arrow.
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
    public void levelUp() {
        super.levelUp();
        arrowsCount += 10 * level;
        attack += 2 * level;
        defense += level;
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
    public void castAbility(/*insert parameters*/) throws Exception {
        if (arrowsCount == 0)
            throw new Exception(/*Insert appropriate message here*/);
        else if (/*There is no enemy within range*/true)
            throw new Exception(/*Insert appropriate message here*/);
        else {
            arrowsCount--;
            //Deal damage equals to attack points to the closest enemy within range (The enemy will try to
            //defend itself).
        }
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%14s", "Level: ") + level + String.format("%16s", "Experience: ") + experience+"/"+experienceThreshold +
                String.format("%15s", "Arrows: ") + arrowsCount + String.format("%14s", "Range: ") + range;
        //returns full information on the current unit.
        //Use it to print the information of each unit during combat / on player turn.
    }
}
