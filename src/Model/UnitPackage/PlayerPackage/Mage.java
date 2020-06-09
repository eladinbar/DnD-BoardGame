package Model.UnitPackage.PlayerPackage;

import Model.TilePackage.EmptyTile;
import Model.TilePackage.Visitor;
import Model.TilePackage.Wall;
import Model.UnitPackage.Unit;

import java.awt.Point;

public class Mage extends Player {
    //Special ability: Blizzard, randomly hit enemies within range for an amount equals to the mage’s
    //spell power at the cost of mana.
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
    public void levelUp() {
        super.levelUp();
        manaPool += 25 * level;
        currentMana = Math.min(currentMana + manaPool/4, manaPool);
        spellPower += 10 * level;
    }

    public void onGameTick() {
        currentMana = Math.min(manaPool, currentMana + level);
    }

    @Override
    public void castAbility(/*insert parameters*/) throws Exception {
        if (currentMana < manaCost)
            throw new Exception(/*Insert appropriate message*/);
        else {
            currentMana -= manaCost;
            Integer hits = 0;
            while (hits < hitsCount /*& There exists a living enemy within range*/) {
                //Select random enemy within range
                //Deal damage (reduce health value) to the chosen enemy for an amount equal to spell power
                //(each enemy may try to defend itself).
                hits++;
            }
        }
    }

    @Override
    public String describe() {
        return String.format("%-15s", name) + "Health: " + currentHealth+"/"+healthPool + String.format("%14s", "Attack: ") + attack + String.format("%14s", "Defense: ")
                + defense + String.format("%14s", "Level: ") + level + String.format("%17s", "Experience: ") + experience+"/"+experienceThreshold +
                String.format("%13s", "Mana: ") + currentMana+"/"+manaPool+" " + String.format("%16s", "Spell Power: ") + spellPower;
        //returns full information on the current unit.
        //Use it to print the information of each unit during combat / on player turn.
    }

    @Override
    public void visit(Unit unit) {

    }

    @Override
    public void visit(Wall wall) {

    }

    @Override
    public void visit(EmptyTile emptyTile) {

    }

    @Override
    public void accept(Visitor visitor) {

    }
}
