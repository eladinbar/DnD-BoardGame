package Model;

public class Mage extends Player {
    //Special ability: Blizzard, randomly hit enemies within range for an amount equals to the mage’s
    //spell power at the cost of mana.
    Integer manaPool;
    Integer currentMana;
    Integer manaCost;
    Integer spellPower;
    Integer hitsCount;
    Integer abilityRange;

    public Mage() {
        //insert initializers
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
    public void castAbility() throws Exception {
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
}
